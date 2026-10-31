import { createRoot } from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import { registerSW } from 'virtual:pwa-register';

// Register Service Worker with auto-update
const updateSW = registerSW({
  immediate: true,
  onNeedRefresh() {
    console.info('Nueva versión de Andean Culinary disponible.');
    // Automatically apply update or refresh
    updateSW(true);
  },
  onOfflineReady() {
    console.info('Andean Culinary: Aplicación y catálogo listos para operar sin conexión.');
  },
});

createRoot(document.getElementById('root')!).render(<App />);
