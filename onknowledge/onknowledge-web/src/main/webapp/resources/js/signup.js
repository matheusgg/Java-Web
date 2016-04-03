var $image;

$(document).ready(function() {
	signupPageSetUp();
});

function signupPageSetUp(){
	initPage();
	setupPasswordStrength();
}

function handleSelectedProfilePictureFile(inputFile) {
	var file = inputFile.files[0];
	if (file.size <= 2097152) { //2MB
		uploadProfilePicture();
		$("#profilePictureFile").val('');
	} else {
		$("#signupErrorMessages").text($(inputFile).data("max-length-message"));
	}
}

function createCropper(data) {
	var $modal = $("#cropperModal");
	$image = $("#cropPicture");
	$modal.on("shown.bs.modal", function() {
		$image.cropper();
	}).on("hidden.bs.modal", function() {
		$image.cropper("destroy");
	});
	openCropperModal();
}

function openCropperModal() {
	$('#cropperModal').modal({
		backdrop : 'static',
		keyboard : false
	}, 'toggle');
}

function getCoords() {
	var data = $image.cropper("getData");
	$("#coords").val(
			data.x + "_" + data.y + "_" + data.width + "_" + data.height);
}

function setupPasswordStrength() {
	/*
	 * Se a senha nao possuir nenhum digito e seu tamanho for menor do que 8
	 * caracteres, ela sera considerada fraca.
	 */
	var weak = new RegExp("^(?=.*\\d{1,}).{8,}$");

	/*
	 * Se o tamanho da senha for igual ou maior do que 8 caracteres e possuir
	 * pelo menos um digito e pelo menos uma letra minuscula e uma letra
	 * maiuscula ou um caractere especial, esta senha sera considerada forte.
	 */
	var strong = new RegExp(
			"^(?=.*[a-z])(?=.*[A-Z]).+|(?=.*[!,%,&,@,#,$,^,*,?,_,~,\\-]).+$");

	$("#senha").keyup(function() {
		testPasswordStrength($(this), weak, strong);
	});
	
	$("#senha").change(function() {
		testPasswordStrength($(this), weak, strong);
	});
}

function testPasswordStrength(pwd, weak, strong) {
	var value = pwd.val();
	var $indicator = $("#" + pwd.data("indicator"));
	removeAllPwdClasses($indicator);

	if (value.length > 0) {
		if (!weak.test(value)) {
			$indicator.addClass("week-password");
			return;
		}

		if (!strong.test(value)) {
			$indicator.addClass("medium-password");
			return;
		}

		$indicator.addClass("strong-password");
	}
}

function removeAllPwdClasses(indicator) {
	indicator.removeClass("week-password");
	indicator.removeClass("medium-password");
	indicator.removeClass("strong-password");
}