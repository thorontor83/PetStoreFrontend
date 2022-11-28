import { useForm } from "react-hook-form";
import React, { useState } from "react";
import ImageDropZone from '../components/ImageDropZone';
import { Button } from '@chakra-ui/react';
import axios from "axios";
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
} from "../components/styles/OrderInput.styled";

const UPLOAD_URL = "http://localhost:8080/upload";




export default function Upload() {

    const bodyFormData = new FormData();
    var body;



    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const [image, setImage] = useState();
    const customConfig = {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    };

    const onSubmit = data => (console.log(data),


        body = { image: image },
        console.log(image),
        axios.post(UPLOAD_URL, body, customConfig)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            }))



    return (
        <>
            <form onSubmit={handleSubmit(onSubmit)} encType="multipart/form-data">
                <FormInputRow>
                    <FormLabel>Upload pet's image</FormLabel>
                    <ImageDropZone value={image} onChange={setImage} />
                    <Button marginBottom={4} mt={4} colorScheme='teal' type="submit" >Upload</Button>
                </FormInputRow>
            </form>
        </>
    );
}