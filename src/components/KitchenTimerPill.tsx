import React, { useState, useEffect } from 'react';
import { Play, Pause, RotateCcw, Plus, X, Bell, Volume2, VolumeX, Maximize2, Minimize2 } from 'lucide-react';
import { soundFx } from '../utils/audio';

export interface ActiveTimer {
  id: string;
  label: string;
  recipeTitle?: string;
  totalSeconds: number;
  remainingSeconds: number;
  isRunning: boolean;
}

interface KitchenTimerPillProps {
  timer: ActiveTimer | null;
  onUpdateTimer: React.Dispatch<React.SetStateAction<ActiveTimer | null>>;
}

export const KitchenTimerPill: React.FC<KitchenTimerPillProps> = ({
  timer,
  onUpdateTimer,
}) => {
  const [isExpanded, setIsExpanded] = useState(false);
  const [soundEnabled, setSoundEnabled] = useState(soundFx.isEnabled());
  const [hasFinishedNotification, setHasFinishedNotification] = useState(false);

  useEffect(() => {
    if (!timer || !timer.isRunning) return;

    const interval = setInterval(() => {
      onUpdateTimer((prev) => {
        if (!prev || !prev.isRunning) return prev;
        if (prev.remainingSeconds <= 1) {
          soundFx.playTimerCompleteChime();
          setHasFinishedNotification(true);
          return {
            ...prev,
            remainingSeconds: 0,
            isRunning: false,
          };
        }
        return {
          ...prev,
          remainingSeconds: prev.remainingSeconds - 1,
        };
      });
    }, 1000);

    return () => clearInterval(interval);
  }, [timer?.isRunning, onUpdateTimer]);

  if (!timer) return null;

  const minutes = Math.floor(timer.remainingSeconds / 60);
  const seconds = timer.remainingSeconds % 60;
  const formattedTime = `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;

  const progressFraction = timer.totalSeconds > 0
    ? (timer.totalSeconds - timer.remainingSeconds) / timer.totalSeconds
    : 1;

  const handleToggleRunning = () => {
    soundFx.playClick();
    onUpdateTimer({
      ...timer,
      isRunning: !timer.isRunning,
    });
    if (hasFinishedNotification) setHasFinishedNotification(false);
  };

  const handleAddMinute = () => {
    soundFx.playClick();
    onUpdateTimer({
      ...timer,
      totalSeconds: timer.totalSeconds + 60,
      remainingSeconds: timer.remainingSeconds + 60,
      isRunning: true,
    });
    setHasFinishedNotification(false);
  };

  const handleReset = () => {
    soundFx.playClick();
    onUpdateTimer({
      ...timer,
      remainingSeconds: timer.totalSeconds,
      isRunning: false,
    });
    setHasFinishedNotification(false);
  };

  const handleClose = () => {
    soundFx.playClick();
    onUpdateTimer(null);
  };

  const handleToggleSound = () => {
    const newState = soundFx.toggleSound();
    setSoundEnabled(newState);
  };

  const isCompleted = timer.remainingSeconds === 0;

  return (
    <div className="fixed bottom-20 md:bottom-6 right-4 z-50 transition-all duration-300">
      {!isExpanded ? (
        /* Compact Floating Pill with Maíz Morado (#581C87) progress accent */
        <div
          className={`group flex items-center gap-3 pl-3.5 pr-2 py-2 rounded-full shadow-xl backdrop-blur-md border border-[#581C87]/20 transition-all ${
            isCompleted
              ? 'bg-[#581C87] text-white animate-bounce'
              : 'bg-[#FEF9F1]/95 text-[#1D1C17] hover:shadow-2xl'
          }`}
        >
          {/* Progress Ring */}
          <div className="relative w-7 h-7 flex items-center justify-center">
            <svg className="w-7 h-7 -rotate-90">
              <circle
                cx="14"
                cy="14"
                r="11"
                className={isCompleted ? 'stroke-white/30' : 'stroke-[#EDE6DC]'}
                strokeWidth="2.5"
                fill="none"
              />
              <circle
                cx="14"
                cy="14"
                r="11"
                className={`transition-all duration-500 ${
                  isCompleted ? 'stroke-white' : 'stroke-[#581C87]'
                }`}
                strokeWidth="2.5"
                strokeDasharray={69.1}
                strokeDashoffset={69.1 * (1 - progressFraction)}
                strokeLinecap="round"
                fill="none"
              />
            </svg>
            <Bell
              className={`w-3.5 h-3.5 absolute ${
                isCompleted ? 'text-white animate-pulse' : 'text-[#581C87]'
              }`}
            />
          </div>

          {/* Time & Label */}
          <button
            type="button"
            onClick={() => setIsExpanded(true)}
            className="text-left flex flex-col justify-center focus-visible:outline-none"
          >
            <span className="text-xs font-semibold uppercase tracking-wider text-[#7940a9] line-clamp-1 max-w-[120px]">
              {timer.label}
            </span>
            <span
              className={`text-base font-bold tabular-nums leading-tight ${
                isCompleted ? 'text-white' : 'text-[#1D1C17]'
              }`}
            >
              {formattedTime}
            </span>
          </button>

          {/* Quick Play/Pause */}
          <button
            type="button"
            onClick={handleToggleRunning}
            className={`w-8 h-8 rounded-full flex items-center justify-center transition-transform active:scale-90 ${
              isCompleted
                ? 'bg-white text-[#581C87]'
                : timer.isRunning
                ? 'bg-[#EDE6DC] text-[#1D1C17] hover:bg-[#E7E0D2]'
                : 'bg-[#581C87] text-white hover:bg-[#6e3900]'
            }`}
            aria-label={timer.isRunning ? 'Pausar' : 'Reanudar'}
          >
            {timer.isRunning ? <Pause className="w-3.5 h-3.5" /> : <Play className="w-3.5 h-3.5 ml-0.5" />}
          </button>

          {/* Expand */}
          <button
            type="button"
            onClick={() => setIsExpanded(true)}
            className="w-7 h-7 rounded-full flex items-center justify-center text-[#887364] hover:text-[#1D1C17]"
            title="Expandir temporizador"
          >
            <Maximize2 className="w-3.5 h-3.5" />
          </button>

          {/* Dismiss */}
          <button
            type="button"
            onClick={handleClose}
            className="w-7 h-7 rounded-full flex items-center justify-center text-[#887364] hover:text-[#AC3400]"
            title="Cerrar temporizador"
          >
            <X className="w-3.5 h-3.5" />
          </button>
        </div>
      ) : (
        /* Expanded Drawer Card */
        <div className="w-80 rounded-3xl bg-[#FEF9F1] border border-[#581C87]/20 p-5 shadow-2xl space-y-4">
          <div className="flex items-center justify-between border-b border-[#EDE6DC] pb-3">
            <div className="flex items-center gap-2">
              <span className="w-2.5 h-2.5 rounded-full bg-[#581C87] animate-ping" />
              <h4 className="text-sm font-bold text-[#1D1C17] font-display">Temporizador de Cocina</h4>
            </div>
            <div className="flex items-center gap-1">
              <button
                type="button"
                onClick={handleToggleSound}
                className="w-8 h-8 rounded-full flex items-center justify-center text-[#554336] hover:bg-[#EDE6DC]"
                title={soundEnabled ? 'Silenciar campanilla' : 'Activar sonido'}
              >
                {soundEnabled ? <Volume2 className="w-4 h-4" /> : <VolumeX className="w-4 h-4 text-[#ba1a1a]" />}
              </button>
              <button
                type="button"
                onClick={() => setIsExpanded(false)}
                className="w-8 h-8 rounded-full flex items-center justify-center text-[#554336] hover:bg-[#EDE6DC]"
                title="Minimizar a píldora flotante"
              >
                <Minimize2 className="w-4 h-4" />
              </button>
              <button
                type="button"
                onClick={handleClose}
                className="w-8 h-8 rounded-full flex items-center justify-center text-[#554336] hover:text-[#AC3400] hover:bg-[#ffdad6]"
                title="Cerrar temporizador"
              >
                <X className="w-4 h-4" />
              </button>
            </div>
          </div>

          <div>
            <p className="text-xs font-semibold text-[#7940a9] uppercase tracking-wider">{timer.label}</p>
            {timer.recipeTitle && (
              <p className="text-xs text-[#554336] truncate">{timer.recipeTitle}</p>
            )}
            <div className="mt-2 text-center py-2 bg-[#F5F0E8] rounded-2xl border border-[#EDE6DC]">
              <span className="text-4xl font-extrabold font-display tabular-nums text-[#1D1C17] tracking-tight">
                {formattedTime}
              </span>
              {isCompleted && (
                <p className="text-xs font-semibold text-[#581C87] mt-1 animate-pulse">
                  ¡Tiempo cumplido! Listo para el siguiente paso
                </p>
              )}
            </div>
          </div>

          {/* Controls */}
          <div className="flex items-center justify-center gap-2 pt-1">
            <button
              type="button"
              onClick={handleToggleRunning}
              className="flex-1 py-2.5 px-4 rounded-full bg-[#581C87] text-white font-medium text-xs flex items-center justify-center gap-2 hover:bg-[#6e3900] shadow-md shadow-[#581C87]/20 active:scale-95 transition-all"
            >
              {timer.isRunning ? (
                <>
                  <Pause className="w-4 h-4" /> Pausar
                </>
              ) : (
                <>
                  <Play className="w-4 h-4" /> Iniciar
                </>
              )}
            </button>

            <button
              type="button"
              onClick={handleAddMinute}
              className="py-2.5 px-3 rounded-full bg-[#EDE6DC] text-[#1D1C17] font-medium text-xs flex items-center gap-1 hover:bg-[#E7E0D2] active:scale-95 transition-all"
              title="Añadir 1 minuto extra"
            >
              <Plus className="w-3.5 h-3.5" /> 1 min
            </button>

            <button
              type="button"
              onClick={handleReset}
              className="w-10 h-10 rounded-full bg-[#EDE6DC] text-[#554336] flex items-center justify-center hover:bg-[#E7E0D2] active:scale-95 transition-all"
              title="Reiniciar tiempo"
            >
              <RotateCcw className="w-4 h-4" />
            </button>
          </div>
        </div>
      )}
    </div>
  );
};
