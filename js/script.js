window.onload = function () {

    // Login function validation
    $("#loginForm").submit(function (event) {
        var valid = true;

        var emailInput = $("#emailLoginInput").val();
        var passwordInput = $("#passwordLoginInput").val();

        // console.log(validateEmail(emailInput));
        if (!validateEmail(emailInput)) {
            valid = false;
            event.preventDefault();
        }
    });

    // Register function validation
    $("#registerForm").submit(function (event) {
        var valid = true;

        var nameInput = $("#nameInput").val();
        var passwordInput = $("#passwordInput").val();
        var confirmPasswordInput = $("#confirmPasswordInput").val();
        var dateOfBirth = $("#birthdayInput").val();
        var cpf = $("#cpfInput").val().replace("-", "");
        var cep = $("#cepInput").val().replace("-", "");
        var state = $("#stateInput option:selected").val();
        var email = $("#emailInput").val();
        var cidade = $("#cityInput").val();


        // Name
        if (!validateName(nameInput)) {
            errorOn("#nameInput", "Nome Invalido");
            valid = false;
        } else {
            errorOff("#nameInput");
        }

        // Password
        if (!validatePassword(passwordInput)) {
            errorOn("#passwordInput", "");
            valid = false;
        } else {
            errorOff("#passwordInput");
        }

        // Birthday
        if (!validateDateOfBirth(dateOfBirth)) {
            errorOn("#birthdayInput", "Nascimento Invalido");
            valid = false;
        } else {
            errorOff("#birthdayInput");
        }

        // CPF
        if (!validateCPF(cpf)) {
            errorOn("#cpfInput", "CPF Invalido");
            valid = false;
        } else {
            errorOff("#cpfInput");
        }

        // CEP
        if (!validateCEP(cep, state)) {
            errorOn("#cepInput", "CEP Invalido");
            valid = false;
        } else {
            errorOff("#cepInput");
        }

        // Email
        if (!validateEmail(email)) {
            errorOn("#emailInput", "Email Invalido");
            valid = false;
        } else {
            errorOff("#emailInput");
        }

        // Password check
        if (!confirmPassword(passwordInput, confirmPasswordInput)) {
            errorOn("#confirmPasswordInput", "Senhas não conferem");
            valid = false;
        } else {
            errorOff("#confirmPasswordInput");
        }

        // check if is empty just to change the class input-error/correct
        if (cidade === "") {
            errorOn("#cityInput", "Cidade Invalida");
            valid = false;

        } else {
            errorOff("#cityInput");
        }

        // If any error has ocurred then prevent submit
        if (valid === false) {
            event.preventDefault();
        }

    });

    $("#contactForm").submit(function (event) {

        var valid = false;

        var name = $("#nameInput").val();
        var email = $("#emailInput").val();
        var phone = $("#phoneInput").val();
        var message = $("#messageInput").val();
                
        // name validation
        if (!validateName(name)) {
            errorOn("#nameInput", "Nome Inválido");
            valid = false;
        } else {
            errorOff("#nameInput");
        }
        
        // email validation
        if (!validateEmail(email)) {
            errorOn("#emailInput", "Email Inválido");
            valid = false;
        } else {
            errorOff("#emailInput");
        }
        
        // check if the text is too short
        if (message.length < 15) {
            errorOn("#messageInput", "Texto muito curto");
            valid = false;
        } else {
            errorOff("#messageInput");
        }
        
        if (phone.length === 0) {
            errorOn("#phoneInput", "Número inválido");
            valid = false;
        } else {
            errorOff("#phoneInput");
        }
        
        if (!valid) {
            console.log(valid);
            event.preventDefault();
        }
    });


    $("#mainLoginForm").submit(function (event) {

        var valid = false;

        var email = $("#emailInput").val();
        var senha = $("#passwordInput").val();
        
        // email validation
        if (!validateEmail(email)) {
            errorOn("#emailInput", "Email Inválido");
            valid = false;
        } else {
            errorOff("#emailInput");
        }
                
        if (senha === "") {
            errorOn("#passwordInput", "Senha é obrigatória!");
            valid = false;
        } else {
            errorOff("#passwordInput");
        }
        
        if (!valid) {
            console.log(valid);
            event.preventDefault();
        }
    });


    // Function to check password Strength in real time

    // Register function validation
    $("#reservationForm").submit(function (event) {
        var valid = true;

        var dataEntrada = $("#dataEntradaInput").val();
        var dataSaida = $("#dataSaidaInput").val();
        var adultos = $("#adultsInput").val();
        var criancasAte3 = $("#criancasAte3Input").val();
        var criancasAte12 = $("#criancasAte12Input").val();

        // Check-In date - validates if date is more than 2 days from now
        if (!validateCheckInOutDate(dataEntrada)) {
            errorOn("#dataEntradaInput", "Data Inválida - a data mínima é 2 dias após hoje");
            valid = false;
        } else {
            errorOff("#dataEntradaInput");
        }

        // Check-out date - validates if date is more than 2 days from now
        if (!validateCheckInOutDate(dataSaida)) {
            errorOn("#dataSaidaInput", "Data Inválida - a data mínima é 2 dias após hoje");
            valid = false;
        } else {
            errorOff("#dataSaidaInput");
        }

        // Check-out date - validates if date is more than 2 days from check-in date
        if (!validateCheckOutDate(dataEntrada,dataSaida)) {
            errorOn("#dataSaidaInput", "Data Inválida - A reserva deve ter pelo menos 2 dias");
            valid = false;
        } else {
            errorOff("#dataSaidaInput");
        }

        // Adults
        if (!validateQtd(adultos,1,4)) {
            errorOn("#adultsInput", "O número de adultos deve ser entre 1 e 4");
            valid = false;
        } else {
            errorOff("#adultsInput");
        }

        // Kids max 3
        if (!validateQtd(adultos,1,4)) {
            errorOn("#criancasAte3Input", "O número de crianças até 3 anos deve ser entre 0 e 3");
            valid = false;
        } else {
            errorOff("#criancasAte3Input");
        }

        // Kids max 12
        if (!validateQtd(adultos,1,4)) {
            errorOn("#criancasAte12Input", "O número de crianças até 12 anos deve ser entre 0 e 4");
            valid = false;
        } else {
            errorOff("#criancasAte12Input");
        }

        // If any error has ocurred then prevent submit
        if (valid === false) {
            event.preventDefault();
        }

    });


    $("#passwordInput").keyup(function () {
        var p = $("#pwdStrenght");
        var str = passwordStrength($(this).val());
        var msg = "";
        var pClass = "";

        switch (str) {
        case "weak":
            msg = "Senha Fraca";
            pClass = 'pwdWeak';
            break;
        case "medium":
            msg = "Senha Média";
            pClass = 'pwdMedium';

            break;
        case "strong":
            msg = "Senha Forte";
            pClass = 'pwdStrong';

            break;
        case "invalid":
            msg = "Senha Inválida (deve possuir entre 6 e 12 caracteres)";
            pClass = 'pwdInvalid';

            break;
        default:
            break;

        };
        // console.log(msg);
        p.text(msg);
        p.removeClass();
        p.addClass(pClass);
    });

};

function errorOn(field, text) {
    $(field).removeClass("input-correct");
    $(field).addClass("input-error");
    $(field + "Error").text(text);
}

function errorOff(field) {
    $(field).removeClass("input-error");
    $(field).addClass("input-correct");
    $(field + "Error").text("");
}

function validateQtd(qtd, minValue, maxValue){
    if (qtd >= minValue && qtd <= maxValue){
        return true;
    }else{
        return false;
    }
}

function validateEmail(email) {
    // Email Regex used to test input
    var regex = /^[a-z]{1}(([.]){0,}[a-z0-9]){0,}@([a-z])+\.([a-z]{2,4})(\.[a-z]{2,4}){0,1}$/;
    return regex.test(email);
}

function validateName(name) {
    var regex = /^[a-zA-Z]{3}[a-zA-Z]*(\s[a-zA-Z]{3}[a-zA-Z]*){1,}$/;
    return regex.test(name);
}

function validatePassword(password) {
    var valid = false;
    if (password.length >= 6 && password.length <= 12) {
        valid = true;
    }
    return valid;
}



function validateCEP(CEP, state) {

    var valid = false;
    console.log("CEP: " + CEP);
    console.log("State: " + state);


    if (!(state === "") && !(CEP === "")) {
        // table of ceps for each state
        var tableOfCEP = {
            SP: '010-199',
            RJ: '200-289',
            ES: '290-299',
            MG: '300-399',
            BA: '400-489',
            SE: '490-499',
            PE: '500-569',
            AL: '570-579',
            PB: '580-589',
            RN: '590-599',
            CE: '600-639',
            PI: '640-649',
            MA: '650-659',
            PA: '660-688',
            AP: '689-689',
            AM: '690-698',
            RR: '693-693',
            AC: '699-699',
            DF: '700-736',
            GO: '728-767',
            TO: '770-779',
            MT: '780-788',
            RO: '789-789',
            MS: '790-799',
            PR: '800-879',
            SC: '880-899',
            RS: '900-999'
        };


        // get the min and max value CEP of the entered state
        var minValue = tableOfCEP[state].substr(0, 3);
        var maxValue = tableOfCEP[state].substr(4, 3);

        // get the first 3 digits of the CEP entered
        var CEPinit = CEP.substr(0, 3);

        // if the entered CEP is in the range then the CEP is valid
        if (CEPinit > minValue && CEPinit < maxValue) {
            valid = true;
        }
    }

    return valid;
}

function validateCPF(CPF) {

    var valid = false;
    CPF = CPF.toString();
    var validCPF = CPF.substr(0, 9);
    var sum = 0;

    // 1 5 3 2 5 1 5 3 1 - 12
    // *10 *9 .. *2
    if (CPF.length === 11) {

        // First validation Digit
        for (var i = 0; i < 9; i++) {
            sum = sum + CPF[i] * (10 - i);
        }

        sum = sum % 11;

        if (sum < 2) {
            sum = 0;
        } else {
            sum = 11 - sum;
        }
        validCPF = validCPF + sum;
        sum = 0;


        // Second validation digit
        for (var i = 0; i < 10; i++) {
            sum = sum + CPF[i] * (11 - i);
        }

        sum = sum % 11;

        if (sum < 2) {
            sum = 0;
        } else {
            sum = 11 - sum;
        }

        validCPF = validCPF + sum;

        // Check if the generated CPF is the same one entered in the input field
        if (validCPF === CPF) {
            valid = true;
        }
    }


    return valid;
}

function validateCheckInOutDate(checkInOutDate) {
    var valid = false;
    var dateRegex = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;

    if (dateRegex.test(checkInOutDate)) {
        var date = new Date(checkInOutDate.substr(6, 4), checkInOutDate.substr(3, 2) - 1, checkInOutDate.substr(0, 2));

        var minDate = new Date(+new Date() + (48 * 60 * 60 * 1000));
        //minDate = minDate.setDate(new Date().getTime() + 48 * 60 * 60 * 1000);

        //If the date's year is higher, true
        if (date && date.getFullYear() > minDate.getFullYear()){
            valid = true;
        //If it's the same year, check month and day
        }else if(date.getFullYear() === minDate.getFullYear()){
            //If the date's month is higher, true
            if (date.getMonth() > minDate.getMonth()){
                valid = true;
            //If it's the same month, check day
            }else if(date.getMonth() === minDate.getMonth()){
                //If the date's day is higher or the same, true
                if (date.getDate() >= minDate.getDate()){
                    valid = true;
                }
            }
        }

    }
    return valid;
}

function validateCheckOutDate(checkInDate,checkOutDate) {

    var valid = false;
    var dateRegex = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;

    if (dateRegex.test(checkInDate) && dateRegex.test(checkOutDate)) {

        var date = new Date(checkOutDate.substr(6, 4), checkOutDate.substr(3, 2) - 1, checkOutDate.substr(0, 2));
        var minDate = new Date(checkInDate.substr(6, 4), checkInDate.substr(3, 2) - 1, checkInDate.substr(0, 2));
        
        minDate.setDate(minDate.getDate() + 2);

        //If the date's year is higher, true
        if (date.getFullYear() > minDate.getFullYear()){
            valid = true;
        //If it's the same year, check month and day
        }else if(date.getFullYear() === minDate.getFullYear()){
            //If the date's month is higher, true
            if (date.getMonth() > minDate.getMonth()){
                valid = true;
            //If it's the same month, check day
            }else if(date.getMonth() === minDate.getMonth()){
                //If the date's day is higher or the same, true
                if (date.getDay() >= minDate.getDay()){
                    valid = true;
                }
            }
        }

    }
    return valid;
}

function validateDateOfBirth(dateOfBirth) {
    var valid = false;
    var dateRegex = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;

    if (dateRegex.test(dateOfBirth)) {
        var date = new Date(dateOfBirth.substr(6, 4), dateOfBirth.substr(3, 2) - 1, dateOfBirth.substr(0, 2));
        var currentDate = new Date();

        // check inferior limit
        if (date.getFullYear() >= 1900 && date.getFullYear() <= currentDate.getFullYear()) {
            // check superior limit
            if (currentDate.getYear() === date.getYear()) {
                if (currentDate.getMonth() >= date.getMonth()) {
                    if (currentDate.getDate() >= date.getDate()) {
                        valid = true;
                    }
                }
            } else {
                valid = true;
            }
        }
    }
    return valid;
}

function regexInString(string, regex) {
    var count = 0;

    for (var i = 0; i < string.length; i++) {
        if (string[i].match(regex)) {
            count++;
        }
    }
    return count;
}

// Return true if there is at least two different special chars
// in the string
function hasTwoDifferentSpecialChars(string) {
    var count = 0;

    var specialChars = [];
    var regex = /^[!@#\$%\^\&*\(\)+=._-]$/;

    for (var i = 0; i < string.length; i++) {
        if (string[i].match(regex)) {
            specialChars.push(string[i]);
        }
    }

    if (specialChars.length > 1) {
        for (var i = 0; i < specialChars.length; i++) {
            for (var j = i; j < specialChars.length; j++) {
                if (specialChars[i] !== specialChars[j]) {
                    return true;
                }
            }
        }
    }
    return false;
}

function passwordStrength(password) {
    var strength;

    if (password.length < 6 || password.length > 12) {
        strength = 'invalid';
    } else {
        var nDigits = regexInString(password, /^[0-9]$/);
        var nLetters = regexInString(password, /^[a-z]$/);
        var nCapitalLetters = regexInString(password, /^[A-Z]$/);
        var nSpecialCharacters = regexInString(password, /^[!@#\$%\^\&*\(\)+=._-]$/);

        if (password.length === 6) {
            strength = 'weak';
        } else if (nLetters > 0 && nDigits > 0 && nCapitalLetters > 0 && hasTwoDifferentSpecialChars(password)) {
            strength = 'strong';
        } else if ((nLetters + nCapitalLetters) > 0 && nDigits > 0 && nSpecialCharacters > 0) {
            strength = 'medium';
        } else {
            strength = 'weak';
        }
    }
    return strength;
}

function confirmPassword(password, confirmPassword) {
    var valid = false;

    if (!(confirmPassword === "")) {
        if (password === confirmPassword) {
            valid = true;
        }
    }
    return valid;
}