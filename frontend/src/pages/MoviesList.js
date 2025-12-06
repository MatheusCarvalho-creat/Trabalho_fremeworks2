import React, {useEffect, useState} from 'react';
import api from '../services/api';
import { List, ListItem, ListItemText, CircularProgress, Alert } from '@mui/material';
import { Link } from 'react-router-dom';

export default function MoviesList(){
  const [movies,setMovies]=useState([]);
  const [loading,setLoading]=useState(false);
  const [error,setError]=useState('');

  useEffect(()=>{ fetchMovies(); },[]);

  const fetchMovies = async () => {
    setLoading(true); setError('');
    try{
      const res = await api.get('/movies');
      setMovies(res.data.content || res.data);
    }catch(err){
      setError('Erro ao carregar filmes');
    }finally{ setLoading(false); }
  };

  if(loading) return <CircularProgress />;
  if(error) return <Alert severity="error">{error}</Alert>;

  return (
    <div>
      <h2>Filmes</h2>
      <List>
        {movies.map(m => (
          <ListItem key={m.id} component={Link} to={'/movies/'+m.id} button>
            <ListItemText primary={m.title} secondary={m.genre} />
          </ListItem>
        ))}
      </List>
    </div>
  );
}
