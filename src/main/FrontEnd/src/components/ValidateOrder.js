import axios from "axios";

const UrlRegisterOrder = "http://localhost:8080/api/v1/store/order";


export default function ValidateOrder({ e, pet, quantity, date }) {
	if (!pet) {
		return 'Pet required';
	}
	if (!quantity)
		return 'Specify a quantity';
	 
	if (!date) {
		return 'Select a date';
	}

	axios.post(UrlRegisterOrder, {
		petId: pet.value,
		quantity: quantity,
		shipDate: date,
		orderStatus: "PLACED",
		complete: false,
	})
		.then(function (response) {
			console.log(response);

		})
		.catch(function (error) {
			console.log(error);
			return 'The order was not created';
		})

	e.target.reset();


	return null;

}