window.onload = function () {

    // Login function validation
    $("#loginForm").submit(function (event) {
        var valid = true;

        var emailInput = $("#emailLoginInput").val();
        var passwordInput = $("#passwordLoginInput").val();

        console.log(validateEmail(emailInput));
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
        var email = $("#emailInput").val();
        var cidade = $("#cityInput").val();


        // Name
        if (!validateName(nameInput)) {
            $("#nameInput").removeClass("input-correct");
            $("#nameInput").addClass("input-error");

            valid = false;
        } else {

            $("#nameInput").removeClass("input-error");
            $("#nameInput").addClass("input-correct");
        }

        // Password
        if (!validatePassword(passwordInput)) {
            $("#passwordInput").removeClass("input-correct");
            $("#passwordInput").addClass("input-error");

            valid = false;
        } else {
            $("#passwordInput").removeClass("input-error");
            $("#passwordInput").addClass("input-correct");
        }

        // Birthday
        if (!validateDateOfBirth(dateOfBirth)) {
            $("#birthdayInput").removeClass("input-correct");
            $("#birthdayInput").addClass("input-error");
            valid = false;
        } else {
            $("#birthdayInput").removeClass("input-error");
            $("#birthdayInput").addClass("input-correct");
        }

        // CPF
        if (!validateCPF(cpf)) {
            $("#cpfInput").removeClass("input-correct");
            $("#cpfInput").addClass("input-error");
            valid = false;
        } else {
            $("#cpfInput").removeClass("input-error");
            $("#cpfInput").addClass("input-correct");
        }
        
        // CEP
        if (!validateCEP(cep)) {
            $("#cepInput").removeClass("input-correct");
            $("#cepInput").addClass("input-error");
            valid = false;
        } else {
            $("#cepInput").removeClass("input-error");
            $("#cepInput").addClass("input-correct");
        }

        // Email
        if (!validateEmail(email)) {
            $("#emailInput").removeClass("input-correct");
            $("#emailInput").addClass("input-error");
            valid = false;
        } else {
            $("#emailInput").removeClass("input-error");
            $("#emailInput").addClass("input-correct");
        }

        // Password check
        if (!confirmPassword(passwordInput, confirmPasswordInput)) {
            $("#confirmPasswordInput").removeClass("input-correct");
            $("#confirmPasswordInput").addClass("input-error");
            valid = false;
        } else {
            $("#confirmPasswordInput").removeClass("input-error");
            $("#confirmPasswordInput").addClass("input-correct");
        }

        // check if is empty just to change the class input-error/correct
        if (cidade === "") {
            $("#cityInput").removeClass("input-correct");
            $("#cityInput").addClass("input-error");
        } else {
            $("#cityInput").removeClass("input-error");
            $("#cityInput").addClass("input-correct");
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
        console.log(msg);
        p.text(msg);
        p.removeClass();
        p.addClass(pClass);
    });

};

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

function validateCEP(cep){
    var valid = false;
    
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