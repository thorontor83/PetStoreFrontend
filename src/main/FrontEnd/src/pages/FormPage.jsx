import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import ImageDropZone from '../components/ImageDropZone';
import { FormLabel, Button } from '@chakra-ui/react';
import axios from "axios";

const UPLOAD_URL = "http://localhost:8080/api/v1/upload";

export function FormPage() {

    return (

        //<!-- saved from url=(0033)http://localhost:8080/uploadimage -->
        <html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /></head>
            <body>
                <section class="my-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 mx-auto">
                                <h2>Upload Image Example</h2>

                                <form method="post" action="http://localhost:8080/upload" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <input type="file" name="image" accept="image/*" class="form-control-file" />
                                    </div>
                                    <button type="submit" class="btn btn-primary">Upload image</button>
                                </form>

                            </div>
                        </div>
                    </div>
                </section>
            </body>
        </html>
    )

}