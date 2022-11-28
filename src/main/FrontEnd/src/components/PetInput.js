import { Box } from '@chakra-ui/react';
import { useForm } from "react-hook-form";
import React, { useState, useEffect } from 'react';
import ValidatePet from "./ValidatePet";
import axios from "axios";
import { Button } from '@chakra-ui/react';
import ImageDropZone from '../components/ImageDropZone';
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
    DatePickerWrapper,
} from "./styles/OrderInput.styled";

const UPLOAD_URL = "http://localhost:8080/upload";


export default function PetInput() {

    const [petName, setPetName] = useState();
    const [imageSrc, setImageSrc] = useState();
    const [category, setCategory] = useState([]);
    const [tags, setTags] = useState([]);
    const [petStatus, setPetStatus] = useState();
    const [error, setError] = useState(false);
    const [success, setSuccess] = useState(null);

    const bodyFormData = new FormData();
    var body;



    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const [image, setImage] = useState();
    const customConfig = {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    };

    const upload = (e,data) => (
        e.preventDefault(),
        console.log(data),       
        body = { image: image },
        console.log(image),
        axios.post(UPLOAD_URL, body, customConfig)
            .then(function (response) {
                console.log(response);               
                setImageSrc(image.path);
            })
            .catch(function (error) {
                console.log(error);
            })
            );
        
        



    var data = [];


    const handleSubmitForm = (e) => {   
    setImageSrc(image.path);    
    e.preventDefault();
    const resultError = ValidatePet({petName, imageSrc, category, tags, petStatus});

    if (resultError !== null) {
        setError(resultError);
        return;
    }
    setPetName('');
    setImageSrc('');
    setCategory('');
    setTags('');
    setPetStatus('');
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
        placeholder: (defaultStyles) => {
            return {
                ...defaultStyles,
                width: "100%",
		        height: "35px",
		        color: "grey",
		        paddingleft: "5px",
		        fontsize: "6px",
		        border:"none",
		        marginleft: "10px",
            }},
        margintop: "7px",
        color: "black",
    };

    

    return (
        <FormSection>
            <FormContainer>
                <FormRow>
                    <FormColumn small>
                        <FormTitle>Register a new Pet</FormTitle>
                        <FormWrapper onSubmit={handleSubmitForm}>
                            <FormInputRow >
                                <FormLabel>{"Pet"}</FormLabel>
                                <FormInput
                                    value={petName}
                                    onChange={(e) => setPetName(e.target.value)}
                                    isMulti={false}
                                    placeholder={`Enter name`}                                   
                                    styles={customStyles}
                                    isClearable
                                />
                            </FormInputRow>
                            
                                <FormInputRow>
                                    <FormLabel>Upload pet's image</FormLabel>
                                        <ImageDropZone value={image} onChange={setImage} />
                                    <Button marginBottom={4} mt={4} colorScheme='teal' onClick={upload} >Upload</Button>
                                </FormInputRow>
                            
                            <FormInputRow >
                                <FormLabel>{"Category"}</FormLabel>
                                <FormInput
                                    value={category}
                                    onChange={(e) => setCategory(e.target.value)}
                                    isMulti={false}
                                    placeholder={`Enter category`}                                   
                                    styles={customStyles}
                                    isClearable
                                />
                            </FormInputRow>
                            <FormInputRow >
                                <FormLabel>{"Tags"}</FormLabel>
                                <FormInput
                                    onChange={(e) => setTags(e.target.value)}
                                    placeholder={`Add related tags with a space between them`}
                                    isMulti={true} 
                                    isClearable                                  
                                    styles={customStyles}
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