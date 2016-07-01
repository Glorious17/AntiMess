/**
 * Created by pberlik on 22.06.2016.
 */



window.onload=function()
{
    //Formular bearbeiten
    $('#bearbeiten').click(function(){
        $('#formular-bearbeiten').fadeIn("slow");
    });
    $('#close-bea').click(function(){
        $('#formular-bearbeiten').fadeOut("slow");
    });

    //Formular bearbeiten
    $('#hinzufügen').click(function(){
        $('#formular-hinzufügen').fadeIn("slow");
    });
    $('#close-hinzu').click(function(){
        $('#formular-hinzufügen').fadeOut("slow");
    });

}

function newLagerort() {
    document.getElementById("lagerort").style.visibility = "visible";
    document.getElementById("attribute").style.visibility = "hidden";
    document.getElementById("new").style.visibility = "hidden";
    document.getElementById("back").style.visibility = "visible";
}

function back() {
    document.getElementById("lagerort").style.visibility = "hidden";
    document.getElementById("attribute").style.visibility = "visible";
    document.getElementById("new").style.visibility = "visible";
    document.getElementById("back").style.visibility = "hidden";
}





