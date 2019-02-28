/**
 * 
 */
function checkImageType(fileName) {
	var pattern = /jpg|gif|png|jpeg/;
	return fileName.match(pattern);
}

function getFileInfo(fullName) {
	var fileName, imgsrc, getLink, fileLink;
	if (checkImageType(fullName)) {
		imgsrc = "/springStudy/upload/displayFile?fileName="+fileName;
		fileLink = fullName.substr(14);
		var front = fullName.substr(0,12);
		var end = fullName.substr(14);
		getLink = "/springStudy/upload/displayFile?fileName="+front+end;
	} else {
		fileLink = fullName.substr(12);
		getLink = "/springStudy/upload/displayFile?fileName="+fullName;
	}
	fileName = fileLink.substr(fileLink.indexOf("_") + 1)
	return {fileName : fileName, imgsrc : imgsrc, getLink : getLink, fullName : fullName};
}
