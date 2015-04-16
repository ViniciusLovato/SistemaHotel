window.onload = function () {

    // Login function validation
    $("#loginForm").submit(function (event) {
        var valid = true;

        var emailInput = $("#emailLoginInput").val();
        var passwordInput = $("#passwordLoginInput").val();

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

        console.log(nameInput);
        console.log("password: " + passwordInput);

        if (!validateName(nameInput)) {
            valid = false;
            event.preventDefault();
        }

        var passStrenght = passwordStrength(passwordInput);
        console.log("Strengh:" + passStrenght);

    });

    $("#passwordInput").keyup(function () {
        var p = $("#pwdStrenght");
        var str = passwordStrength($(this).val());
        var msg = "";

        switch (str) {
        case "weak":
            msg = "Senha: Fraca";
            break;
        case "medium":
            msg = "Senha: Média";
            break;
        case "strong":
            msg = "Senha: Forte";
            break;
        case "invalid":
            msg = "Senha: Inválida (deve possuir entre 6 e 12 caracteres)";
            break;
        default:
            break;

        };
        console.log(msg);
        p.text($(this).val() + " : " + msg);
    });

};

function validateEmail(email) {
    // Email Regex used to test input
    var regex = /^[a-z]([a-z0-9.])+@([a-z])+\.([a-z]{2,4})(\.[a-z]{2,4}){0,1}$/;
    return regex.test(email);
}

function validateName(name) {
    var regex = /^[a-zA-Z]{3}[a-zA-Z]*(\s[a-zA-Z]{3}[a-zA-Z]*){1,}$/;
    return regex.test(name);
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

    if (password === confirmPassword) {
        valid = true;
    }

    return valid;

}