import React, {useEffect, useState} from 'react';
import api from '../services/api';
import { useParams, useNavigate } from 'react-router-dom';
import { CircularProgress, Alert, Button } from '@mui/material';

export default function MovieDetails(){
  const { id } = useParams();
  const [movie,setMovie]=useState(null);
  const [loading,setLoading]=useState(false);
  const [error,setError]=useState('');
  const navigate = useNavigate();

  useEffect(()=>{ fetchMovie(); },[]);

  const fetchMovie = async () => {
    setLoading(true); setError('');
    try{
      const res = await api.get('/movies/'+id);
      setMovie(res.data);
    }catch(err){
      setError('Erro ao carregar');
    }finally{ setLoading(false); }
  };

  const remove = async () => {
    try{
      await api.delete('/movies/'+id);
      navigate('/movies');
    }catch(err){
      setError('Erro ao deletar');
    }
  };

  if(loading) return <CircularProgress />;
  if(error) return <Alert severity="error">{error}</Alert>;
  if(!movie) return null;

  return (
    <div>
      <h2>{movie.title}</h2>
      <p>{movie.description}</p>
      <p>Gênero: {movie.genre}</p>
      <Button variant="contained" color="error" onClick={remove}>Deletar</Button>
    </div>
  );
}
