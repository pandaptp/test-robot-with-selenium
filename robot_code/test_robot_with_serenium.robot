*** Settings ***
Library    Selenium2Library
Library    BuiltIn
Library    String
Library  OperatingSystem
Library  Process

*** Variables ***
${url}      http://192.168.0.156:4200/login
${input_user}       //*[@id="username-input"]
${input_pass}       //*[@id="password-input"]
${btn_login}       //*[@id="submit-btn"]
${btn_manage_acc}       //*[@ng-reflect-router-link="/manage-account/manage-account"]
${btn_dropdown}     //*[@id="service-type"]
${btn_type_create_acc}        //*[@value="create-account"]
${username}     demo
${password}     demopassword
${number}     0881235678
${1to1}     1

${input_number}       //*[@id="username-3bb-input"]
${input_email}       //*[@id="email-input"]
${btn_submit}       //*[@id="submit-btn"]

${path_file}        C:\\Users\\DELL-1920\\PycharmProjects\\untitled\\venv\\mycustomer.txt

*** Keywords ***
Input Username and Password
     [Arguments]      ${xpath_user}       ${xpath_pass}     ${username}       ${password}
     Element Should Be Visible    ${xpath_user}
     Element Should Be Visible    ${xpath_pass}
     Input Text       ${xpath_user}       ${username}
     Input Text       ${xpath_pass}       ${password}

Click Button
     [Arguments]       ${btn}
     Element Should Be Visible    ${btn}
     Click Element        ${btn}

Verify Create Excel Sucess
   [Arguments]        ${xpath}
   Element Should Be Visible        ${xpath}

*** Test Cases ***
Create Account - 1:1
    [Tags]      success
    Open Browser        about:blank     Chrome
    Go To       ${url}
    Input Username and Password     ${input_user}     ${input_pass}      ${username}      ${password}
    Click Button        ${btn_login}
    Click Button        ${btn_manage_acc}
    Wait Until Page Contains     Manage Account
    Click Button        ${btn_dropdown}
    Click Button        ${btn_type_create_acc}
    Input Username and Password     ${input_number}     ${input_email}      ${number}      testrobot.orcsoft2@gmail.com
    Click Button        ${btn_submit}

    Run Process     python      get_total_email.py      alias=myproc
    ${result} =   Get process Result      myproc

    Log     Result is ${result.rc}
    Log     Result is ${result.stdout}
    Log     Result is ${result.stderr}

    Run Process     python      read_and_write.py       ${1to1}     ${result.stdout}      alias=myproc2
    ${result2} =   Get process Result      myproc2

    Log     Result is ${result2.rc}
    Log     Result is ${result2.stdout}
    Log     Result is ${result2.stderr}

Create Account - Batch File
    [Tags]      success
    Open Browser        about:blank     Chrome
    Go To       ${url}
    Input Username and Password     ${input_user}     ${input_pass}      ${username}      ${password}
    Click Button        ${btn_login}
    Click Button        ${btn_manage_acc}
    Wait Until Page Contains     Manage Account
    Click Button        //*[@ng-reflect-router-link="/manage-account/manage-multipl"]
    Choose File     //*[@name="file"]     ${path_file}

    Run Process     python      check_line.py    ${path_file}      alias=myproc
    ${result_line} =   Get process Result      myproc
    Log     Result is ${result_line.rc}
    Log     Result is ${result_line.stdout}
    Log     Result is ${result_line.stderr}

    Run Process     python      get_total_email.py      alias=myproc2
    ${result_total} =   Get process Result      myproc2
    Log     Result is ${result_total.rc}
    Log     Result is ${result_total.stdout}
    Log     Result is ${result_total.stderr}

    Input Text      //*[@formcontrolname="batchUserEmail"]      testrobot.orcsoft2@gmail.com
    Click Button       //*[@id="submit-btn"]

    Run Process     python      read_and_write.py    ${result_line.stdout}      ${result_total.stdout}      alias=myproc3
    ${result_batch} =   Get process Result      myproc3

    Log     Result is ${result_batch.rc}
    Log     Result is ${result_batch.stdout}
    Log     Result is ${result_batch.stderr}