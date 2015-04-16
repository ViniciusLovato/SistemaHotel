window.onload = function () {

    // Login function validation
    $("#loginForm").submit(function (event) {
        var valid = true;

        var emailInput = $("#emailInput").val();
        var passwordInput = $("#passwordInput").val();

        if (!validateEmail(emailInput)) {
            valid = false;
            event.preventDefault();
        }
    });
    
    // Register function validation
    $("#registerForm").submit(function(event){
        var valid = true;
        
        var nameInput = $("#nameInput").val();
        
        console.log(nameInput);
        
        if(!validateName(nameInput)){
            valid = false;
            event.preventDefault();
        }
        else {
            nameInput.
            
        }
        
        console.log(valid);
    });

};

function validateEmail(email) {
    // Email Regex used to test input
    var regex = /^[a-z]([a-z0-9.])+@([a-z])+\.([a-z]{2,4})(\.[a-z]{2,4}){0,1}$/;
    return regex.test(email);
}

function validateName(name){
    var regex = /^[a-zA-Z]{3}[a-zA-Z]*\s[a-zA-Z]{3}[a-zA-Z]*$/;
    return regex.test(name);
}

function passwordStrength(password){
    var strength;
    
    
    
    
    return strength;
    
}