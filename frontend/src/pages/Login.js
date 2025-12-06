import React, {useState} from 'react';
import api from '../services/api';
import { TextField, Button, Box, Alert } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export default function Login(){
  const [username,setUsername]=useState('');
  const [password,setPassword]=useState('');
  const [error,setError]=useState('');
  const navigate = useNavigate();

  const submit = async () => {
    setError('');
    try{
      const res = await api.post('/auth/login',{username,password});
      localStorage.setItem('token', res.data.token);
      navigate('/movies');
    }catch(err){
      setError(err.response?.data || 'Erro ao autenticar');
    }
  };

  return (
    <Box sx={{maxWidth:400, mx:'auto'}}>
      <h2>Login</h2>
      {error && <Alert severity="error">{error}</Alert>}
      <TextField label="Usuário" fullWidth sx={{my:1}} value={username} onChange={e=>setUsername(e.target.value)} />
      <TextField label="Senha" type="password" fullWidth sx={{my:1}} value={password} onChange={e=>setPassword(e.target.value)} />
      <Button variant="contained" onClick={submit}>Entrar</Button>
    </Box>
  );
}
