import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email import encoders

def send_email(temp):

    SUBJECT = "Email Data"

    msg = MIMEMultipart()
    msg['Subject'] = SUBJECT
    msg['From'] = 'testrobot.orcsoft@gmail.com'
    msg['To'] = 'testrobot2.orcsoft@gmail.com'

    part = MIMEBase('application', "octet-stream")
    part.set_payload(open(temp, "rb").read())
    encoders.encode_base64(part)

    part.add_header('Content-Disposition', 'attachment; filename=user_password.xlsx')

    msg.attach(part)

    server = smtplib.SMTP('smtp.gmail.com', 587)
    server.connect("smtp.gmail.com", 587)
    server.ehlo()
    server.starttls()
    server.ehlo()
    server.login('testrobot.orcsoft@gmail.com', 'Password2019')
    server.sendmail('testrobot.orcsoft@gmail.com','testrobot.orcsoft2@gmail.com', msg.as_string())