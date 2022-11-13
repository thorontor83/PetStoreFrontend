import { useForm } from "react-hook-form";
import React, { useState } from "react";
import ImageDropZone from '../components/ImageDropZone';
import { FormLabel, Button } from '@chakra-ui/react';
import axios from "axios";

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
        /*axios({
            method: "post",
            url: UPLOAD_URL,
            data: {"image":data[0]},
            headers: { "Content-Type": "multipart/form-data" },
          })
            .then(function (response) {
              //handle success
              console.log(response);
            })
            .catch(function (response) {
              //handle error
              console.log(response);
            }))*/




        axios.post(UPLOAD_URL, body, customConfig)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            }))



    return (
        <>
            <h1>Upload Page</h1>
            <form onSubmit={handleSubmit(onSubmit)} encType="multipart/form-data">
                <fieldset>
                    <div>
                        <FormLabel>Upload your image</FormLabel>
                        <ImageDropZone value={image} onChange={setImage} />
                        <Button mt={4} colorScheme='teal' type="submit" >Upload</Button>
                    </div>
                </fieldset>
            </form>
        </>
    );
}