import { Flex } from './styles/Flex.styled';
import axios from "axios";
import React, { useState, useEffect } from 'react';
import AsyncSelect from "react-select/async";
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
import RegisterUser from '../pages/RegisterUser';
import { set } from 'react-hook-form';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const urlPetCatalog = "http://localhost:8080/api/v1/pet";



export default function OrderInput() {

    const [pets, setPets] = useState([]);
    const [pet, setPet] = useState([]);
    const [quantity, setQuantity] = useState([]);
    const [date, setDate] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);

    var data = [];

    useEffect(() => {
        const getPets = async () => {
            const arr = [];
            await axios.get(urlPetCatalog).then((res) => {
                let result = res.data;
                result.map((pet) => {
                    return arr.push({ value: pet.id, label: pet.petName });
                });
                setPets(arr);        
                console.log(pets);

            });
        };
        getPets();
    }, []);

    const handlePetChange = (selectedOption) => {
        /*console.log("handleTeacherChange", selectedOption);*/
        setPet(selectedOption);        

    }

    const loadPets = (searchValue, callback) => {
        const filteredPets = pets.filter(option => option.label.toLowerCase().includes(searchValue.toLowerCase())
        );
        /*console.log('loadTeachers', searchValue, filteredTeachers);*/
        callback(filteredPets);
    }


    const handleSubmit = (e) => {
        e.preventDefault();
        const resultError = ValidateForm({ pet, quantity, date });

        if (resultError !== null) {
            setError(resultError);
            return;
        }
        setPet('');
        setQuantity('');
        setDate('');
        setError(null);
        setSuccess('Application was submitted!');

    };

    const messageVariants = {
        hidden: { y: 30, opacity: 0 },
        animate: { y: 0, opacity: 1, transition: { delay: 0.2, duration: 0.4 } },
    };


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
                        <FormTitle>Place your Order</FormTitle>
                        <FormWrapper onSubmit={handleSubmit}>

                            <FormInputRow >
                                <FormLabel>{"Pet"}</FormLabel>
                                <AsyncSelect
                                    onChange={handlePetChange}
                                    loadOptions={loadPets}
                                    placeholder={`Select your pet`}                                   
                                    styles={customStyles}
                                />
                            </FormInputRow>
                            <FormInputRow >
                                <FormLabel>{"Quantity"}</FormLabel>
                                <FormInput
                                    onChange={setQuantity}
                                    placeholder={`Select the quantity`}                                   
                                    styles={customStyles}
                                />
                            </FormInputRow>
                            <FormInputRow >
                                <FormLabel>{"Date of Delivery"}</FormLabel>
                                <DatePicker 
                                selected={date} 
                                onChange={setDate}
                                dateFormat='dd/MM/yyyy'
                                minDate={new Date()}
                                isClearable
                                />
                            </FormInputRow>
                            <FormButton type="submit">Submit Order</FormButton>
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