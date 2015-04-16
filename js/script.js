window.onload = function () {

    // Login function validation
    $("#registerForm").submit(function () {
        var valid = true;
        
        var emailInput = $("#emailInput").val();
        var passwordInput = $("#passwordInput").val();

        console.log(emailInput);
        console.log(passwordInput);
        
        // Email Regex used to test input
        var regex = /[a-z][a-z0-9]+@[a-z]+.[a-z]{2,4}/;

        // Email validation
        if (regex.test(emailInput) === false) {
            console.log("email invalido");
            valid = false;   
        }
        
        // Password validation
        if(passwordInput === ""){
            console.log("Digite uma senha");
            valid = false;
        }

        if(!valid){
            event.preventDefault();
        }
        console.log(valid);
    });


};