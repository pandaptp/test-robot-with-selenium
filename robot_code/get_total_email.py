import smtplib
import time
import imaplib
import email

ORG_EMAIL   = "@gmail.com"
FROM_EMAIL  = "testrobot.orcsoft2" + ORG_EMAIL
FROM_PWD    = "Password2019"
SMTP_SERVER = "imap.gmail.com"
SMTP_PORT   = 993

def check_new_email():
    mail = imaplib.IMAP4_SSL(SMTP_SERVER)
    mail.login(FROM_EMAIL, FROM_PWD)
    mail.select("inbox", readonly=True)
    result, data = mail.search(None, "ALL")
    mail_ids = data[0]  # data is a list.
    id_list = mail_ids.split()  # ids is a space separated string
    latest_email_uid = int(id_list[-1])
    print(latest_email_uid)

check_new_email()
