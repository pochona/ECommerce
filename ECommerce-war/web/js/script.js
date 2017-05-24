$( document ).ready(function() {
   $("#creaCompte").click(function() {
      $("#form-login").hide();
      $("#form-crea").show();
   });
   
   $("#connexionBtn").click(function() {
      $("#form-login").show();
      $("#form-crea").hide();
   });
   
});