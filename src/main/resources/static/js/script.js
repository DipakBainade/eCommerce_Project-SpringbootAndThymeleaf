$(function () {
	var $userRegister = $("#userRegister");

	$userRegister.validate({
		rules: {
			name: {
				required: true,
				lettersonly: true
			},
			email: {
				required: true,
				space: true,
				email: true
			},
			mobileNumber: {
				required: true,
				space: true,
				numericOnly: true,
				minlength: 10,
				maxlength: 12
			},
			password: {
				required: true,
				space: true
			},
			confirmpassword: {
				required: true,
				space: true,
				equalTo: '#pass'
			},
			address: {
				required: true,
				all: true
			},
			city: {
				required: true,
				space: true
			},
			state: {
				required: true,
				space: true
			},
			pincode: {
				required: true,
				space: true,
				numericOnly: true
			},
			img: {
				required: true
			}
		},
		messages: {
			name: {
				required: 'Name is required',
				lettersonly: 'Invalid name'
			},
			email: {
				required: 'Email is required',
				space: 'Spaces not allowed',
				email: 'Invalid email format'
			},
			mobileNumber: {
				required: 'Mobile number is required',
				space: 'Spaces not allowed',
				numericOnly: 'Invalid mobile number',
				minlength: 'Minimum 10 digits required',
				maxlength: 'Maximum 12 digits allowed'
			},
			password: {
				required: 'Password is required',
				space: 'Spaces not allowed'
			},
			confirmpassword: {
				required: 'Confirm password is required',
				space: 'Spaces not allowed',
				equalTo: 'Password mismatch'
			},
			address: {
				required: 'Address is required',
				all: 'Invalid address'
			},
			city: {
				required: 'City is required',
				space: 'Spaces not allowed'
			},
			state: {
				required: 'State is required',
				space: 'Spaces not allowed'
			},
			pincode: {
				required: 'Pincode is required',
				space: 'Spaces not allowed',
				numericOnly: 'Invalid pincode'
			},
			img: {
				required: 'Image is required'
			}
		}
	});
});

jQuery.validator.addMethod('lettersonly', function (value, element) {
	return /^[a-zA-Z\s-]+$/.test(value);
}, 'Letters only please');

jQuery.validator.addMethod('space', function (value, element) {
	return /^\S+$/.test(value);
}, 'Spaces are not allowed');

jQuery.validator.addMethod('all', function (value, element) {
	return /^[a-zA-Z0-9_,.\s-]+$/.test(value);
}, 'Invalid characters');

jQuery.validator.addMethod('numericOnly', function (value, element) {
	return /^[0-9]+$/.test(value);
}, 'Digits only');