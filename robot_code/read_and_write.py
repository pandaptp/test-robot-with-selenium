# -*- coding: utf-8 -*-
import smtplib
import time
import imaplib
import email
import sys
import send_excel

import xlsxwriter
from email.header import decode_header
from robot.libraries.BuiltIn import BuiltIn

ORG_EMAIL   = "@gmail.com"
FROM_EMAIL  = "testrobot.orcsoft2" + ORG_EMAIL
FROM_PWD    = "Password2019"
SMTP_SERVER = "imap.gmail.com"
SMTP_PORT   = 993

marked_leading_str = "Username:"
marked_trailing_str = "Password:"
marked_trailing2_str = "Thankyou"

# -------------------------------------------------
#
# Utility to read email from Gmail Using Python
#
# ------------------------------------------------

def read_email_from_gmail(total_loop):
    tmp_email_list = []
    try:
        for index in range(1, total_loop + 1):
            mail = imaplib.IMAP4_SSL(SMTP_SERVER)
            mail.login(FROM_EMAIL, FROM_PWD)
            mail.select('inbox')
            types, data = mail.search(None, 'ALL')
            mail_ids = data[0]
            id_list = mail_ids.split()
            # first_email_id = int(id_list[0])
            latest_email_id = int(id_list[int(-index)])
            # print("-index: " + str(int(-index)))
            # print("latest_email_id: " + str(latest_email_id))
            typ, data = mail.fetch(str(latest_email_id), '(RFC822)')
            msg = email.message_from_string(data[0][1].decode('utf-8'))
            # print('\r\n', msg['from'])
            # print('\r\n', msg['to'])
            # print('\r\n', msg['subject'])
            print(msg.get_payload())
            # This for Do loop
            tmp_email_list.append(msg.get_payload())
        return tmp_email_list

    except Exception as err:
        print('Handling run-time error:', err)

def check_new_email():
    expect_email_uid = int(sys.argv[1]) + int(sys.argv[2])
    count = 0
    while True:
        count = count + 1
        if count == 15:
            break
        mail = imaplib.IMAP4_SSL(SMTP_SERVER)
        mail.login(FROM_EMAIL, FROM_PWD)
        mail.select("inbox", readonly=True)
        result, data = mail.search(None, "ALL")
        mail_ids = data[0]  # data is a list.
        id_list = mail_ids.split()  # ids is a space separatesd string
        if int(id_list[-1]) == expect_email_uid:
            read_and_write()
            break
        else:
            time.sleep(
                6)  # put your value here, be sure that this value is sufficient ( see @tripleee comment below)

def read_and_write():
    texts = read_email_from_gmail(int(sys.argv[1]))
    username_pass_list = []
    for text in texts:
        clean_text = text.replace(" ", "")

        start_index_username = clean_text.find(marked_leading_str)
        end_index_username = clean_text.find(marked_trailing_str)

        start_index_pass = clean_text.find(marked_trailing_str)
        end_index_pass = clean_text.find(marked_trailing2_str)

        # print("Username: " + clean_text[int(start_index_username + len(marked_leading_str)):end_index_username])
        # print("Password: " + clean_text[int(start_index_pass + len(marked_trailing_str)):end_index_pass])

        username_str = clean_text[int(start_index_username + len(marked_leading_str)):end_index_username]
        password_str = clean_text[int(start_index_pass + len(marked_trailing_str)):end_index_pass]

        tmp_dict = {'Username': username_str, 'Password': password_str}
        username_pass_list.append(tmp_dict)

    # Create an new Excel file and add a worksheet.
    workbook = xlsxwriter.Workbook('user_and_pass.xlsx')
    worksheet = workbook.add_worksheet()

    # Widen the first column to make the text clearer.
    worksheet.set_column('A:B', 20)

    # Write some simple text.
    worksheet.write('A1', 'Username')

    # Text with formatting.
    worksheet.write('B1', 'Password')

    # Write some numbers, with row/column notation.
    # First row is 0
    for i in range(1, len(username_pass_list) + 1):
        worksheet.write(i, 0, username_pass_list[i - 1]['Username'])
        worksheet.write(i, 1, username_pass_list[i - 1]['Password'])

    print("Success for create Excel file")
    workbook.close()
    temp = "user_and_pass.xlsx"
    send_excel.send_email(temp)
    print("Send file excel")

# Firstly
check_new_email()