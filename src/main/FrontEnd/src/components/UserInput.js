import { Flex } from './styles/Flex.styled';
import React, { useState } from 'react';
import Select from 'react-select'
import {
    FormContainer,
    FormColumn,
    FormWrapper,
    FormInput,
    FormSection,
    FormRow,
    FormLabel,
    FormInputRow,
    FormMessage,
    FormButton,
    FormTitle,
} from "./styles/UserInput.styled";
import ValidateForm from "./ValidateForm";




export default function UserInput() {

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [address, setAddress] = useState('');
    const [role, setRole] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPass, setConfirmPass] = useState('');
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);


    const handleSubmit = (e) => {
        e.preventDefault();
        const resultError = ValidateForm({ username, email, address, role, password, confirmPass });

        if (resultError !== null) {
            setError(resultError);
            return;
        }
        setUsername('');
        setEmail('');
        setAddress('');
        setPassword('');
        setConfirmPass('');
        setRole('');
        setError(null);
        setSuccess('Application was submitted!');

    };

    const messageVariants = {
        hidden: { y: 30, opacity: 0 },
        animate: { y: 0, opacity: 1, transition: { delay: 0.2, duration: 0.4 } },
    };

    const formData = [
        { label: 'Name', value: username, onChange: (e) => setUsername(e.target.value), type: 'text' },
        { label: 'Email', value: email, onChange: (e) => setEmail(e.target.value), type: 'email' },
        { label: 'Address', value: address, onChange: (e) => setAddress(e.target.value), type: 'text' },
        {
            label: 'Password',
            value: password,
            onChange: (e) => setPassword(e.target.value),
            type: 'password',
        },
        {
            label: 'Confirm Password',
            value: confirmPass,
            onChange: (e) => setConfirmPass(e.target.value),
            type: 'password',
        },
    ];


    const options = [
        { value: 'USER', label: 'User' },
        { value: 'ADMIN', label: 'Admin' }];

    const customStyles = {
        option: (provided, state) => ({
            ...provided,
            fontsize: "11px",
            margintop: "7px",
            borderBottom: '1px dotted pink',
            color: state.isSelected ? "black" : "grey",
            padding: 20,
        }),
        singleValue: (provided, state) => {
            const opacity = state.isDisabled ? 0.5 : 1;
            const transition = 'opacity 300ms';

            return { ...provided, opacity, transition };
        },
        fontsize: "11px",
        margintop: "7px",
        color: "black",
    };

    return (
        <FormSection>
            <FormContainer>
                <FormRow>
                    <FormColumn small>
                        <FormTitle>Sign up</FormTitle>
                        <FormWrapper onSubmit={handleSubmit}>
                            {formData.map((el, index) => (
                                <FormInputRow key={index}>
                                    <FormLabel>{el.label}</FormLabel>
                                    <FormInput
                                        options={el.options}
                                        type={el.type}
                                        placeholder={`Enter your ${el.label.toLocaleLowerCase()}`}
                                        value={el.value}
                                        onChange={el.onChange}
                                    />
                                </FormInputRow>
                            ))}
                            <FormLabel>Role</FormLabel>
                            <Select styles={customStyles} placeholder="Select your role" type={"text"}
                                options={options} label={"Role"} onChange={(e) => setRole(e.value)}></Select>

                            <FormButton type="submit">Signup</FormButton>
                        </FormWrapper>
                        {error && (
                            <FormMessage
                                variants={messageVariants}
                                initial="hidden"
                                animate="animate"
                                error
                            >
                                {error}
                            </FormMessage>
                        )}
                        {success && (
                            <FormMessage
                                variants={messageVariants}
                                initial="hidden"
                                animate="animate"
                            >
                                {success}
                            </FormMessage>
                        )}
                    </FormColumn>
                </FormRow>
            </FormContainer>
        </FormSection>
    );
}