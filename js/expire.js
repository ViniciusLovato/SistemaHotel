/* 
    Based on solution provided on StackOverflow
    http://stackoverflow.com/questions/667555/detecting-idle-time-in-javascript-elegantly
*/
var idleTime = 0;
var reset = false;

$(document).ready(function () {
    //Increment the idle time counter every minute.
    var idleInterval = setInterval(timerIncrement, 1000);//60000); // 1 minute

    //Zero the idle timer on mouse movement.
    $(this).mousemove(function (e) {
        if (reset)
            reset = false;
        else
            idleTime = 0;
    });
    $(this).keypress(function (e) {
        if (reset)
            reset = false;
        else
            idleTime = 0;
    });
});

function timerIncrement() {
    if ($('#autenticado').val() === 'true'){
        idleTime = idleTime + 1;

        $('#timer').text(idleTime);

        if (idleTime === 15) { // 55 minutes - show alert!
            var resp = confirm("Sua sessão vai expirar. Pressione OK para renová-la.");
            if (resp){
                idleTime = 0;
            }else{
                reset = true;
            }
        }else if (idleTime === 20) { // 60 minutes - logout

            $.get('/SistemaHotel/login?exit=true');

            window.location.reload();
        }
    }

}