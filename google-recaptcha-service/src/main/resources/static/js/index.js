function myFunction() {
	var formId = "#signup-form";
	
	var $captcha = $( '#recaptcha' ),
    response = grecaptcha.getResponse();
	console.log('g-recaptcha-response: ' + response);
	alert(response);
}