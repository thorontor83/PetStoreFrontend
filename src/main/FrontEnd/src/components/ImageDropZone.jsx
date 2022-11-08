import { useDropzone } from 'react-dropzone';
import React, { useCallback, useState } from 'react';
import styled from 'styled-components';
import { Spinner, Image } from '@chakra-ui/react';
import placeholder from "../resources/images/placeholder.png";


const Dropzone = styled.div`
    border: 1px dashed #ced4d9;
    border-radius: 5px;
    color: #6c757d;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 142px;
    img{
        height:140px;
    }
    `;

function ImageDropzone({ value, onChange }) {

    const [loading, setLoading] = useState(false);


    const onDrop = useCallback((acceptedFiles) => {
        setLoading(true);
        console.log(acceptedFiles);
        onChange(acceptedFiles[0].path);
        console.log(value);
        setLoading(false)
    }, []);


    const { getRootProps, getInputProps } = useDropzone({
        onDrop,
        multiple: false,
        accept: 'image/*',
    });



    return (
        <>
            <Dropzone {...getRootProps()} >
                <input {...getInputProps()} />
                {
                    value ? (
                        <img src={require(`../resources/images/${value}`)} />
                    ) : loading ? (
                        <Spinner variant="standard" animation="border" role="status" />
                    ) : (
                        <span>Drag & Drop file here, or click to select image</span>
                    )

                }
            </Dropzone>
        </>
    )
}

export default ImageDropzone;