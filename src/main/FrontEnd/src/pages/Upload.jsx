import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import ImageDropZone from '../components/ImageDropZone';
import { FormLabel } from '@chakra-ui/react';


export default function Upload() {

    const [imagePath, setImagePath] = useState();

    return (
        <div>
            <h1>Upload Page</h1>
            <ImageDropZone value={imagePath} onChange={setImagePath} />
        </div>
    );
}