import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UploadPage from "./pages/Upload";
import { FormPage } from "./pages/FormPage";
import { PetCatalog } from "./pages/PetCatalog";


function App() {



  return (
<>
<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/pets" element={<PetCatalog/>} />
  <Route path="/upload" element={<UploadPage />} />
  <Route path="/form" element={<FormPage/>} />
</Routes>
</>
  );
}

export default App;
