$( document ).ready(function() {
   $("#creaCompte").click(function() {
      $("#form-login").hide();
      $("#form-crea").show();
   });
   
   $("#connexionBtn").click(function() {
      $("#form-login").show();
      $("#form-crea").hide();
   });
   
   $("#btn-crediter").click(function(){
       $("#form-crediter").show();
       $("#form-dediter").hide();
   });
   
   $("#btn-debiter").click(function(){
       $("#form-crediter").hide();
       $("#form-dediter").show();
   });
});