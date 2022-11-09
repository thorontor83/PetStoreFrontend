import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UploadPage from "./pages/Upload";
import { FormPage } from "./pages/FormPage";


function App() {



  return (
<>
<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/upload" element={<UploadPage />} />
  <Route path="/form" element={<FormPage/>} />
</Routes>
</>
  );
}

export default App;
