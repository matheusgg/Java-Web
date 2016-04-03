$(document).ready(function() {
	initOkEditors();
});

/**
 * @param inputFile
 * @param uploadFunction
 * @param errorMessageSection
 * @returns {Boolean}
 */
function uploadSelectedFiles(inputFile, uploadFunction, errorMessageSection) {
	var files = inputFile.files;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		if (file.size > 10485760) { // 10MB
			$(errorMessageSection).text(
					$(inputFile).data("exceeded-max-file-size") + file.name);
			$(inputFile).val("");
			return false;
		}
	}
	uploadFunction();
}

