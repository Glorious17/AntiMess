/**
 *
 * Zufallsfarbe im Hex-Format berechnen
 *
 * @return String Gibt eine Zufallsfarbe im Hex-Format
 *                zurück z.B. #EFEFEF
 *
 */


 window.onload=function()
{
 zufallsFarbe();
}
 
function zufallsFarbe() {
    var farbe = "#";

    for (var f = 1; f <= 6; f++){
    	for (var i in [0, 1, 2]) {
	        farbe += (Math.ceil(255 * Math.random()).toString(16).toUpperCase());
	    }
	    //document.body.style.backgroundColor = farbe;
	    document.getElementById("bilds"+f).style.backgroundColor = farbe;  
    return farbe;
    }
}