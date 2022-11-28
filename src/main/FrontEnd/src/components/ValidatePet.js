import axios from "axios";

const UrlRegisterPet = "http://localhost:8080/api/v1/pet";


export default function ValidatePet({ e, petName, imageSrc, category, tags, petStatus }) {
	if (!petName) {
		return 'Pet required';
	}
	if (!imageSrc)
		return 'Upload a image';
	 
	if (!category) {
		return 'Add a category';
	}
    if(!tags) {
        
        return 'Add tags';
    }
    let tagList = tags.split(" ");
    var categoryObject = {};
    categoryObject.id = "";
    categoryObject.categoryName = category;

    

	axios.post(UrlRegisterPet, {
		petName: petName,
		imageSrc: imageSrc,
        category: categoryObject,
        tags: tagList,      
		petStatus: "AVAILABLE",
	})
		.then(function (response) {
			console.log(response);

		})
		.catch(function (error) {
			console.log(error);
			return 'The order was not created';
		})

	


	return null;

}