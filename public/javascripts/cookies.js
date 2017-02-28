/**
 * 
 */

function setCookie(key, value) {
    var d = new Date();
    d.setTime(d.getTime() + (365*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = key + "=" + value + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie(key) {
    var movieIdc = getCookie(key);
    if (movieIdc != "") {
        return true;
    } else {
        return false;
    }
}

function deleteCookie(key) {
    var d = new Date();
    d.setTime(d.getTime() - (365*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = key + "=" + "" + "; " + expires;
}