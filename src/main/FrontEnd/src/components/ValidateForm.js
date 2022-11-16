import axios from "axios";

const UrlRegisterUser = "http://localhost:8080/api/v1/customer";


export default function ValidateForm({ username, email, address, password, confirmPass, role }) {
	if (!username.trim()) {
		return 'Username required';
	}
	const regex =
		/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if (!email) {
		return 'Email required';
	} else if (regex.test(email.toLocalLowerCase)) {
		return 'Email address is invalid';
	}
	if (!address) {
		return 'Address required';
	}
	if (!password) {
		return 'Password is required';
	} else if (password.length < 6) {
		return 'Password needs to be 6 characters or more';
	}

	if (!confirmPass) {
		return 'Enter Confirm password is required';
	} else if (confirmPass !== password) {
		return 'Passwords do not match';
	}
	if (!role) {
		return 'Role required';
	}



	axios.post(UrlRegisterUser, {
		username: username,
		email: email,
		address: address,
		password: password,
		confirmPass: confirmPass,
		customerRole: role,
	})
		.then(function (response) {
			console.log(response);

		})
		.catch(function (error) {
			console.log(error);
			return 'The user was not created';
		})

	return null;

}