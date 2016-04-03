$(document).ready(function() {
	var $loginForm = $("#login-form").validate({
		// Regras de validacao
		rules : {
			j_username : {
				required : true,
				minlength : 4,
			},
			j_password : {
				required : true,
				minlength : 5,
			}
		},
		// Mensagens de validacao
		messages : {
			j_username : {
				required : $("#usernameLogin").data("required-message"),
				minlength : $("#usernameLogin").data("minlength-message")
			},
			j_password : {
				required : $("#passwordLogin").data("required-message"),
				minlength : $("#passwordLogin").data("minlength-message")
			}
		},
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
});