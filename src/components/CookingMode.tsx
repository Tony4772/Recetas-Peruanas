import React, { useState } from 'react';
import {
  ArrowLeft,
  ChevronLeft,
  ChevronRight,
  Flame,
  CheckCircle2,
  Clock,
  Play,
  Pause,
  RotateCcw,
  Sparkles,
  Award,
  Share2
} from 'lucide-react';
import { Recipe, CookingStep } from '../types';
import { soundFx } from '../utils/audio';

interface CookingModeProps {
  recipe: Recipe;
  onExit: () => void;
  onStartTimer: (label: string, seconds: number, recipeTitle: string) => void;
}

export const CookingMode: React.FC<CookingModeProps> = ({
  recipe,
  onExit,
  onStartTimer,
}) => {
  const [currentStepIdx, setCurrentStepIdx] = useState(0);
  const [completedSteps, setCompletedSteps] = useState<Record<number, boolean>>({});
  const [showCelebration, setShowCelebration] = useState(false);

  // Local step timer state
  const currentStep = recipe.steps[currentStepIdx];
  const [localSecondsLeft, setLocalSecondsLeft] = useState<number | null>(
    currentStep.timerDurationSeconds || null
  );
  const [isLocalTimerRunning, setIsLocalTimerRunning] = useState(false);

  const handleStepChange = (newIdx: number) => {
    soundFx.playClick();
    setCurrentStepIdx(newIdx);
    const newStep = recipe.steps[newIdx];
    setLocalSecondsLeft(newStep.timerDurationSeconds || null);
    setIsLocalTimerRunning(false);
  };

  const handleToggleLocalTimer = () => {
    soundFx.playClick();
    if (!localSecondsLeft && currentStep.timerDurationSeconds) {
      setLocalSecondsLeft(currentStep.timerDurationSeconds);
    }
    setIsLocalTimerRunning(!isLocalTimerRunning);
  };

  React.useEffect(() => {
    if (!isLocalTimerRunning || localSecondsLeft === null) return;
    if (localSecondsLeft <= 0) {
      soundFx.playTimerCompleteChime();
      setIsLocalTimerRunning(false);
      return;
    }

    const interval = setInterval(() => {
      setLocalSecondsLeft((sec) => {
        if (sec === null || sec <= 1) {
          soundFx.playTimerCompleteChime();
          setIsLocalTimerRunning(false);
          return 0;
        }
        return sec - 1;
      });
    }, 1000);

    return () => clearInterval(interval);
  }, [isLocalTimerRunning, localSecondsLeft]);

  const toggleStepDone = (idx: number) => {
    soundFx.playClick();
    setCompletedSteps((prev) => {
      const updated = { ...prev, [idx]: !prev[idx] };
      // If we finished the last step, show celebration!
      if (idx === recipe.steps.length - 1 && updated[idx]) {
        setShowCelebration(true);
        soundFx.playTimerCompleteChime();
      }
      return updated;
    });
  };

  const handleNext = () => {
    soundFx.playClick();
    // mark current step as completed
    setCompletedSteps((prev) => ({ ...prev, [currentStepIdx]: true }));

    if (currentStepIdx < recipe.steps.length - 1) {
      handleStepChange(currentStepIdx + 1);
    } else {
      setShowCelebration(true);
      soundFx.playTimerCompleteChime();
    }
  };

  const handlePrev = () => {
    if (currentStepIdx > 0) {
      handleStepChange(currentStepIdx - 1);
    }
  };

  const progressPercent = Math.round(
    ((currentStepIdx + 1) / recipe.steps.length) * 100
  );

  return (
    <div className="min-h-screen bg-[#FEF9F1] flex flex-col justify-between">
      {/* Top Header */}
      <header className="sticky top-0 z-30 bg-[#FEF9F1]/95 backdrop-blur-md border-b border-[#EDE6DC] px-4 sm:px-8 py-3.5 flex items-center justify-between">
        <button
          type="button"
          onClick={() => {
            soundFx.playClick();
            onExit();
          }}
          className="flex items-center gap-2 text-xs font-bold text-[#554336] hover:text-[#1D1C17] py-2 px-3.5 rounded-full hover:bg-[#EDE6DC] transition-colors"
        >
          <ArrowLeft className="w-4 h-4" />
          <span>Salir del Modo Cocina</span>
        </button>

        <div className="text-center">
          <span className="text-[11px] font-bold uppercase tracking-wider text-[#D97706] block">
            Modo Cocina Guiado
          </span>
          <h2 className="text-sm font-bold text-[#1D1C17] font-display truncate max-w-[220px] sm:max-w-md">
            {recipe.title}
          </h2>
        </div>

        <div className="text-xs font-bold text-[#581C87] bg-[#581C87]/10 px-3 py-1 rounded-full tabular-nums">
          Paso {currentStepIdx + 1} de {recipe.steps.length}
        </div>
      </header>

      {/* Progress Strip */}
      <div className="w-full bg-[#EDE6DC] h-1.5">
        <div
          className="bg-[#D97706] h-full transition-all duration-300"
          style={{ width: `${progressPercent}%` }}
        />
      </div>

      {/* Main Step Content Area */}
      <main className="max-w-4xl mx-auto w-full px-4 sm:px-6 py-6 sm:py-10 flex-1 flex flex-col justify-center">
        {!showCelebration ? (
          <div className="space-y-6">
            {/* Step Navigation Pill Selector */}
            <div className="flex items-center justify-center gap-2 flex-wrap">
              {recipe.steps.map((st, idx) => {
                const isActive = idx === currentStepIdx;
                const isDone = !!completedSteps[idx];
                return (
                  <button
                    key={st.stepNumber}
                    type="button"
                    onClick={() => handleStepChange(idx)}
                    className={`w-10 h-10 rounded-full font-display font-bold text-xs flex items-center justify-center transition-all ${
                      isActive
                        ? 'bg-[#D97706] text-white shadow-md scale-110 ring-4 ring-[#D97706]/20'
                        : isDone
                        ? 'bg-[#15803D] text-white'
                        : 'bg-[#EDE6DC] text-[#554336] hover:bg-[#E7E0D2]'
                    }`}
                    title={`Ir al paso ${st.stepNumber}`}
                  >
                    {isDone ? <CheckCircle2 className="w-4 h-4" /> : st.stepNumber}
                  </button>
                );
              })}
            </div>

            {/* Active Step Card (Styled with 20px radius & internal 2px accent indicator in #D97706) */}
            <div className="rounded-3xl bg-[#F5F0E8] border-2 border-[#D97706] p-6 sm:p-10 shadow-lg relative overflow-hidden transition-all">
              {/* Internal Accent Pill Header */}
              <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-3 mb-6 border-b border-[#EDE6DC] pb-4">
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 rounded-2xl bg-[#D97706] text-white flex items-center justify-center font-display font-black text-lg shadow-sm">
                    {currentStep.stepNumber}
                  </div>
                  <div>
                    <span className="text-[11px] font-bold text-[#8D4B00] uppercase tracking-wider block">
                      Paso actual
                    </span>
                    <h3 className="font-display text-xl sm:text-2xl font-extrabold text-[#1D1C17] leading-snug">
                      {currentStep.title}
                    </h3>
                  </div>
                </div>

                {/* Mark as completed toggle */}
                <button
                  type="button"
                  onClick={() => toggleStepDone(currentStepIdx)}
                  className={`py-2 px-4 rounded-full text-xs font-bold transition-all flex items-center gap-2 ${
                    completedSteps[currentStepIdx]
                      ? 'bg-[#15803D] text-white'
                      : 'bg-[#EDE6DC] text-[#554336] hover:bg-[#E7E0D2]'
                  }`}
                >
                  <CheckCircle2 className="w-4 h-4" />
                  <span>
                    {completedSteps[currentStepIdx] ? 'Paso Completado' : 'Marcar como Hecho'}
                  </span>
                </button>
              </div>

              {/* Glanceable Arm's-Length Typography */}
              <p className="text-base sm:text-xl text-[#1D1C17] leading-relaxed font-normal mb-8">
                {currentStep.instruction}
              </p>

              {/* Step Timer if present */}
              {currentStep.timerDurationSeconds && (
                <div className="mb-6 p-4 sm:p-5 rounded-2xl bg-[#FEF9F1] border border-[#581C87]/20 flex flex-col sm:flex-row items-center justify-between gap-4">
                  <div className="flex items-center gap-3">
                    <div className="w-10 h-10 rounded-full bg-[#581C87]/15 text-[#581C87] flex items-center justify-center">
                      <Clock className="w-5 h-5" />
                    </div>
                    <div>
                      <h4 className="text-xs font-bold text-[#581C87] uppercase tracking-wider">
                        {currentStep.timerLabel || 'Temporizador para este paso'}
                      </h4>
                      <div className="text-2xl sm:text-3xl font-extrabold font-display tabular-nums text-[#1D1C17]">
                        {localSecondsLeft !== null
                          ? `${String(Math.floor(localSecondsLeft / 60)).padStart(2, '0')}:${String(
                              localSecondsLeft % 60
                            ).padStart(2, '0')}`
                          : '--:--'}
                      </div>
                    </div>
                  </div>

                  <div className="flex items-center gap-2">
                    <button
                      type="button"
                      onClick={handleToggleLocalTimer}
                      className="py-2.5 px-5 rounded-full bg-[#581C87] hover:bg-[#6e3900] text-white text-xs font-bold flex items-center gap-2 shadow-sm active:scale-95 transition-all"
                    >
                      {isLocalTimerRunning ? (
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
                      onClick={() => {
                        soundFx.playClick();
                        setLocalSecondsLeft(currentStep.timerDurationSeconds || 0);
                        setIsLocalTimerRunning(false);
                      }}
                      className="p-2.5 rounded-full bg-[#EDE6DC] text-[#554336] hover:bg-[#E7E0D2] transition-colors"
                      title="Reiniciar temporizador"
                    >
                      <RotateCcw className="w-4 h-4" />
                    </button>

                    <button
                      type="button"
                      onClick={() => {
                        soundFx.playClick();
                        onStartTimer(
                          currentStep.timerLabel || `Paso ${currentStep.stepNumber}`,
                          currentStep.timerDurationSeconds!,
                          recipe.title
                        );
                      }}
                      className="py-2.5 px-3 rounded-full bg-[#EDE6DC] hover:bg-[#E7E0D2] text-[#554336] text-xs font-semibold"
                      title="Enviar a píldora flotante persistente"
                    >
                      Flotante
                    </button>
                  </div>
                </div>
              )}

              {/* Chef Secret callout */}
              {currentStep.chefSecret && (
                <div className="p-4 sm:p-5 rounded-2xl bg-[#F8F3EB] border border-[#C2410C]/20 flex items-start gap-3">
                  <div className="w-8 h-8 rounded-full bg-[#C2410C]/15 text-[#C2410C] flex items-center justify-center shrink-0 mt-0.5">
                    <Flame className="w-4 h-4 fill-current" />
                  </div>
                  <div>
                    <h5 className="text-xs font-bold uppercase tracking-wider text-[#C2410C] mb-1">
                      El Secreto del Chef Criollo
                    </h5>
                    <p className="text-xs sm:text-sm text-[#554336] leading-relaxed">
                      {currentStep.chefSecret}
                    </p>
                  </div>
                </div>
              )}
            </div>
          </div>
        ) : (
          /* Finished Celebration View */
          <div className="text-center py-12 px-6 rounded-3xl bg-[#F5F0E8] border border-[#EDE6DC] shadow-xl space-y-6 max-w-xl mx-auto">
            <div className="w-20 h-20 rounded-full bg-[#15803D]/15 text-[#15803D] flex items-center justify-center mx-auto shadow-inner animate-bounce">
              <Award className="w-10 h-10" />
            </div>

            <div>
              <span className="text-xs font-bold uppercase tracking-widest text-[#8D4B00]">
                ¡Misión Culinaria Cumplida!
              </span>
              <h2 className="font-display text-3xl font-extrabold text-[#1D1C17] mt-1">
                ¡Tu plato está listo para servir!
              </h2>
              <p className="text-sm text-[#554336] mt-2 leading-relaxed">
                Has preparado exitosamente <strong>{recipe.title}</strong> con las técnicas tradicionales y secretos de la gastronomía andina.
              </p>
            </div>

            <div className="p-4 bg-[#FEF9F1] rounded-2xl border border-[#EDE6DC] text-left text-xs text-[#554336] space-y-2">
              <strong className="block text-xs font-bold text-[#1D1C17]">
                Sugerencias para el emplatado:
              </strong>
              <p>• Sirve en vajilla precalentada (o fría si es ceviche/causa).</p>
              <p>• Acompaña con el maridaje recomendado: <em>{recipe.pairing.drink}</em>.</p>
              <p>• Disfruta con familia y amigos honrando la tradición peruana.</p>
            </div>

            <div className="flex flex-col sm:flex-row gap-3 pt-2">
              <button
                type="button"
                onClick={() => {
                  soundFx.playClick();
                  onExit();
                }}
                className="flex-1 py-3 px-6 rounded-full bg-[#D97706] text-white font-bold text-sm shadow-md hover:bg-[#B45309] transition-all"
              >
                Volver al Recetario
              </button>
              <button
                type="button"
                onClick={() => {
                  soundFx.playClick();
                  setShowCelebration(false);
                  setCurrentStepIdx(0);
                }}
                className="py-3 px-5 rounded-full border border-[#887364] text-[#1D1C17] hover:bg-[#EDE6DC] font-semibold text-sm transition-colors"
              >
                Repasar Pasos
              </button>
            </div>
          </div>
        )}
      </main>

      {/* Bottom Sticky Action Bar */}
      {!showCelebration && (
        <footer className="sticky bottom-0 z-30 bg-[#FEF9F1]/95 backdrop-blur-md border-t border-[#EDE6DC] p-4">
          <div className="max-w-4xl mx-auto flex items-center justify-between gap-4">
            <button
              type="button"
              onClick={handlePrev}
              disabled={currentStepIdx === 0}
              className="py-3 px-5 rounded-full border-[1.5px] border-[#887364] text-[#1D1C17] hover:bg-[#EDE6DC] disabled:opacity-30 disabled:pointer-events-none font-semibold text-xs sm:text-sm flex items-center gap-1.5 transition-all"
            >
              <ChevronLeft className="w-4 h-4" />
              <span>Anterior</span>
            </button>

            <div className="hidden sm:block text-xs font-semibold text-[#554336]">
              {completedSteps[currentStepIdx] ? '✓ Paso verificado' : 'Sigue la instrucción y avanza'}
            </div>

            <button
              type="button"
              onClick={handleNext}
              className="py-3 px-6 rounded-full bg-[#D97706] hover:bg-[#B45309] text-white font-bold text-xs sm:text-sm shadow-md shadow-[#D97706]/30 flex items-center gap-2 active:scale-95 transition-all"
            >
              <span>{currentStepIdx === recipe.steps.length - 1 ? 'Finalizar Receta' : 'Siguiente Paso'}</span>
              <ChevronRight className="w-4 h-4" />
            </button>
          </div>
        </footer>
      )}
    </div>
  );
};
