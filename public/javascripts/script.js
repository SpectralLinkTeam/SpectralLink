$(document).ready(function(){
   
    $("#webShopLink").click(function(){
        $("#webShopContainer").css("display", "block");
        $("#narudzbeniceContainer").css("display", "none");
    });
    
    
    $("#narudzbeniceLink").click(function(){
        $("#webShopContainer").css("display", "none");
        $("#narudzbeniceContainer").css("display", "block");
    });
});