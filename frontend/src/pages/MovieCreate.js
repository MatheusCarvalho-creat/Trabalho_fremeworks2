import React, {useState} from 'react';
import api from '../services/api';
import { TextField, Button, Box, Alert } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function MovieCreate(){
  const [title,setTitle]=useState('');
  const [description,setDescription]=useState('');
  const [genre,setGenre]=useState('');
  const [error,setError]=useState('');
  const navigate = useNavigate();

  const submit = async () => {
    setError('');
    try{
      await api.post('/movies',{title,description,genre});
      navigate('/movies');
    }catch(err){
      setError('Erro ao criar filme');
    }
  };

  return (
    <Box sx={{maxWidth:600, mx:'auto'}}>
      <h2>Criar Filme</h2>
      {error && <Alert severity="error">{error}</Alert>}
      <TextField label="Título" fullWidth sx={{my:1}} value={title} onChange={e=>setTitle(e.target.value)} />
      <TextField label="Descrição" fullWidth multiline rows={4} sx={{my:1}} value={description} onChange={e=>setDescription(e.target.value)} />
      <TextField label="Gênero" fullWidth sx={{my:1}} value={genre} onChange={e=>setGenre(e.target.value)} />
      <Button variant="contained" onClick={submit}>Criar</Button>
    </Box>
  );
}
