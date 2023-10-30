
import React, {useState, useEffect, useCallback} from 'react';
import {useDropzone} from 'react-dropzone'
import './App.css';
import axios from 'axios';

const UserProfiles = () => {


  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {

    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      console.log(res);
      setUserProfiles(res.data)
    });
  }

  useEffect(() => {
   fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index) => {

    return (
    <div key={index}>
      <br/>
      <h1>{userProfile.userName}</h1>
      <p>{userProfile.userProfileId}</p>
      <Dropzone/>
      <br/>
    </div>
    )
  })
}

function Dropzone() {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag profile image here, or click to add file</p>
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <UserProfiles></UserProfiles>
    </div>
  );
}

export default App;
