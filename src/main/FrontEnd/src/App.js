import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UploadPage from "./pages/Upload";


function App() {



  return (
<>
<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/upload" element={<UploadPage />} />
</Routes>
</>
  );
}

export default App;
