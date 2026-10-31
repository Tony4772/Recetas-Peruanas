import React, { useState } from 'react';
import { WifiOff, Wifi, HardDriveDownload, CheckCircle2, ChevronDown, ChevronUp, ShieldCheck } from 'lucide-react';
import { soundFx } from '../utils/audio';

interface OfflineIndicatorProps {
  isOnline: boolean;
  showReconnectedBanner: boolean;
  onDismissReconnected?: () => void;
  recipesCount?: number;
}

export const OfflineIndicator: React.FC<OfflineIndicatorProps> = ({
  isOnline,
  showReconnectedBanner,
  onDismissReconnected,
  recipesCount = 52,
}) => {
  const [isExpanded, setIsExpanded] = useState(false);

  if (isOnline && !showReconnectedBanner) {
    return null;
  }

  if (showReconnectedBanner) {
    return (
      <aside
        aria-label="Notificación de conectividad"
        className="bg-[#15803D] text-white px-4 py-2.5 text-xs sm:text-sm font-semibold flex items-center justify-between shadow-md transition-all animate-fadeIn"
      >
        <div className="max-w-7xl mx-auto w-full flex items-center justify-between gap-4">
          <div className="flex items-center gap-2">
            <Wifi className="w-4 h-4 text-emerald-200 animate-pulse" />
            <span>¡Conexión a internet restablecida! Todos los recursos siguen disponibles en caché.</span>
          </div>
          {onDismissReconnected && (
            <button
              type="button"
              onClick={onDismissReconnected}
              className="text-emerald-100 hover:text-white underline text-xs font-bold"
            >
              Cerrar
            </button>
          )}
        </div>
      </aside>
    );
  }

  // Offline Mode Banner
  return (
    <aside
      aria-label="Estado sin conexión"
      className="bg-[#9A3412] text-white border-b border-[#7C2D12] shadow-lg transition-all"
    >
      <div className="max-w-7xl mx-auto px-4 sm:px-6 py-2.5">
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-2.5">
          <div className="flex items-center gap-3">
            <div className="w-8 h-8 rounded-full bg-white/15 flex items-center justify-center shrink-0">
              <WifiOff className="w-4 h-4 text-amber-200" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <span className="font-bold text-xs sm:text-sm tracking-tight">
                  Modo Sin Conexión Activo
                </span>
                <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-[10px] font-bold bg-[#FEF3C7] text-[#9A3412]">
                  <HardDriveDownload className="w-3 h-3" /> PWA Cache OK
                </span>
              </div>
              <p className="text-xs text-amber-100 font-normal">
                No hay señal de red, pero las {recipesCount} recetas y el Mercado Andino están listos para cocinar.
              </p>
            </div>
          </div>

          <div className="flex items-center gap-2 self-end sm:self-center">
            <button
              type="button"
              onClick={() => {
                soundFx.playClick();
                setIsExpanded(!isExpanded);
              }}
              className="px-3 py-1 bg-white/10 hover:bg-white/20 rounded-full text-xs font-semibold flex items-center gap-1.5 transition-colors"
            >
              <span>{isExpanded ? 'Ocultar detalles' : 'Ver qué está guardado'}</span>
              {isExpanded ? <ChevronUp className="w-3.5 h-3.5" /> : <ChevronDown className="w-3.5 h-3.5" />}
            </button>
          </div>
        </div>

        {/* Expanded cache inspection info */}
        {isExpanded && (
          <div className="mt-3 pt-3 border-t border-white/15 grid grid-cols-1 sm:grid-cols-3 gap-3 text-xs text-amber-100 animate-fadeIn">
            <div className="flex items-start gap-2 bg-black/15 p-2.5 rounded-xl">
              <CheckCircle2 className="w-4 h-4 text-amber-300 shrink-0 mt-0.5" />
              <div>
                <p className="font-bold text-white">52 Recetas Auténticas</p>
                <p className="text-[11px] text-amber-100/90 leading-tight">
                  Costa, Sierra, Selva, Postres y Bebidas con pasos, macros e ingredientes completos.
                </p>
              </div>
            </div>

            <div className="flex items-start gap-2 bg-black/15 p-2.5 rounded-xl">
              <CheckCircle2 className="w-4 h-4 text-amber-300 shrink-0 mt-0.5" />
              <div>
                <p className="font-bold text-white">Mercado & Botica Andina</p>
                <p className="text-[11px] text-amber-100/90 leading-tight">
                  Guía completa de 6 ajíes nativos y 8 ingredientes de despensa ancestral.
                </p>
              </div>
            </div>

            <div className="flex items-start gap-2 bg-black/15 p-2.5 rounded-xl">
              <ShieldCheck className="w-4 h-4 text-amber-300 shrink-0 mt-0.5" />
              <div>
                <p className="font-bold text-white">Modo Cocina & Timers</p>
                <p className="text-[11px] text-amber-100/90 leading-tight">
                  Temporizadores de cocina y guía paso a paso funcionan sin gastar datos.
                </p>
              </div>
            </div>
          </div>
        )}
      </div>
    </aside>
  );
};
