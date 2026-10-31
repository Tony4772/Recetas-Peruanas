import React, { useState } from 'react';
import {
  ArrowLeft,
  Clock,
  ChefHat,
  Bookmark,
  Share2,
  Check,
  Wine,
  Sparkles,
  Flame,
  Info,
  Timer as TimerIcon,
  Activity,
  UtensilsCrossed,
  ShieldCheck
} from 'lucide-react';
import { Recipe, Ingredient } from '../types';
import { AjiHeatGauge } from './AjiHeatGauge';
import { QuantityStepper } from './QuantityStepper';
import { soundFx } from '../utils/audio';

interface RecipeDetailProps {
  recipe: Recipe;
  isSaved: boolean;
  onBack: () => void;
  onToggleSave: (id: string) => void;
  onStartCooking: (recipe: Recipe) => void;
  onStartTimer: (label: string, seconds: number, recipeTitle: string) => void;
}

export const RecipeDetail: React.FC<RecipeDetailProps> = ({
  recipe,
  isSaved,
  onBack,
  onToggleSave,
  onStartCooking,
  onStartTimer,
}) => {
  const [servings, setServings] = useState<number>(recipe.defaultServings);
  const [checkedIngredients, setCheckedIngredients] = useState<Record<string, boolean>>({});
  const [copiedLink, setCopiedLink] = useState(false);
  const [imageError, setImageError] = useState(false);

  const scaleRatio = servings / recipe.defaultServings;

  const toggleIngredientCheck = (id: string) => {
    soundFx.playClick();
    setCheckedIngredients((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
  };

  const handleShare = () => {
    soundFx.playClick();
    if (navigator.clipboard) {
      navigator.clipboard.writeText(window.location.href);
      setCopiedLink(true);
      setTimeout(() => setCopiedLink(false), 2000);
    }
  };

  const checkedCount = Object.values(checkedIngredients).filter(Boolean).length;
  const totalIngredients = recipe.ingredients.length;
  const completionPercent = Math.round((checkedCount / totalIngredients) * 100);

  return (
    <div className="min-h-screen bg-[#FEF9F1] pb-24">
      {/* Sticky top action bar */}
      <div className="sticky top-0 z-30 bg-[#FEF9F1]/90 backdrop-blur-md border-b border-[#EDE6DC] px-4 sm:px-8 py-3 flex items-center justify-between">
        <button
          type="button"
          onClick={() => {
            soundFx.playClick();
            onBack();
          }}
          className="flex items-center gap-2 text-sm font-semibold text-[#554336] hover:text-[#1D1C17] py-1.5 px-3 rounded-full hover:bg-[#EDE6DC] transition-colors"
        >
          <ArrowLeft className="w-4 h-4" />
          <span>Volver al recetario</span>
        </button>

        <div className="flex items-center gap-2">
          <button
            type="button"
            onClick={handleShare}
            className="w-10 h-10 rounded-full flex items-center justify-center text-[#554336] hover:bg-[#EDE6DC] transition-colors"
            title="Compartir receta"
          >
            <Share2 className="w-4 h-4" />
          </button>
          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onToggleSave(recipe.id);
            }}
            className={`w-10 h-10 rounded-full flex items-center justify-center transition-transform active:scale-90 ${
              isSaved
                ? 'bg-[#C2410C] text-white shadow-md'
                : 'bg-[#EDE6DC] text-[#554336] hover:text-[#1D1C17]'
            }`}
            title={isSaved ? 'Guardada en favoritos' : 'Guardar en favoritos'}
          >
            <Bookmark className={`w-4 h-4 ${isSaved ? 'fill-current' : ''}`} />
          </button>
        </div>
      </div>

      {copiedLink && (
        <div className="fixed top-16 left-1/2 -translate-x-1/2 z-50 px-4 py-2 bg-[#1D1C17] text-white text-xs font-semibold rounded-full shadow-lg">
          ¡Enlace copiado al portapapeles!
        </div>
      )}

      {/* Main container */}
      <main className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 pt-6">
        {/* Hero Section */}
        <div className="rounded-3xl overflow-hidden bg-[#F5F0E8] shadow-md border border-[#EDE6DC] mb-8">
          <div className="grid grid-cols-1 lg:grid-cols-12">
            {/* Visual media */}
            <div className="lg:col-span-7 relative aspect-[16/10] lg:aspect-auto lg:min-h-[420px] overflow-hidden bg-[#E7E2DA]">
              {!imageError ? (
                <img
                  src={recipe.imageUrl}
                  alt={recipe.title}
                  referrerPolicy="no-referrer"
                  onError={() => setImageError(true)}
                  className="w-full h-full object-cover"
                />
              ) : (
                <div className="w-full h-full flex flex-col items-center justify-center p-8 bg-gradient-to-br from-[#F5F0E8] to-[#E7E2DA]">
                  <UtensilsCrossed className="w-12 h-12 text-[#8D4B00] mb-3" />
                  <p className="font-display text-xl font-bold text-[#1D1C17] text-center">{recipe.title}</p>
                  <p className="text-sm text-[#554336] mt-1">{recipe.subtitle}</p>
                </div>
              )}
              <div className="absolute inset-0 bg-gradient-to-t from-[#1D1C17]/80 via-transparent to-transparent lg:hidden" />
              
              {/* Region Pill */}
              <div className="absolute top-4 left-4">
                <span className="px-3.5 py-1.5 rounded-full text-xs font-bold tracking-wide uppercase bg-[#581C87] text-white shadow-md">
                  Región {recipe.region}
                </span>
              </div>
            </div>

            {/* Title & Core Meta */}
            <div className="lg:col-span-5 p-6 sm:p-8 flex flex-col justify-between">
              <div>
                {recipe.quechuaOrLocalName && (
                  <p className="text-xs font-bold uppercase tracking-widest text-[#8D4B00] mb-1">
                    {recipe.quechuaOrLocalName}
                  </p>
                )}
                <h1 className="font-display text-2xl sm:text-3xl font-extrabold text-[#1D1C17] leading-tight mb-2">
                  {recipe.title}
                </h1>
                <p className="text-sm font-medium text-[#554336] leading-relaxed mb-4">
                  {recipe.subtitle}
                </p>

                <div className="flex items-center gap-2 mb-6">
                  <span className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full text-xs font-bold bg-[#15803D]/10 text-[#15803D] border border-[#15803D]/25">
                    <ShieldCheck className="w-3.5 h-3.5" />
                    <span>Disponible 100% Offline</span>
                  </span>
                  <span className="text-xs text-[#887364]">
                    Ingredientes y pasos precacheados
                  </span>
                </div>

                {/* KPI chips */}
                <div className="grid grid-cols-3 gap-2 p-3 bg-[#EDE6DC]/70 rounded-2xl mb-6">
                  <div className="text-center border-r border-[#dbc2b0]/50">
                    <span className="block text-[11px] font-semibold text-[#887364] uppercase">Preparación</span>
                    <span className="text-sm font-bold text-[#1D1C17] tabular-nums">
                      {recipe.prepTimeMinutes} min
                    </span>
                  </div>
                  <div className="text-center border-r border-[#dbc2b0]/50">
                    <span className="block text-[11px] font-semibold text-[#887364] uppercase">Cocción</span>
                    <span className="text-sm font-bold text-[#1D1C17] tabular-nums">
                      {recipe.cookTimeMinutes} min
                    </span>
                  </div>
                  <div className="text-center">
                    <span className="block text-[11px] font-semibold text-[#887364] uppercase">Dificultad</span>
                    <span className="text-sm font-bold text-[#8D4B00]">
                      {recipe.difficulty}
                    </span>
                  </div>
                </div>

                {/* Heat Gauge */}
                <div className="mb-4 p-3 bg-[#FEF9F1] rounded-2xl border border-[#EDE6DC]">
                  <span className="block text-xs font-bold text-[#554336] uppercase tracking-wider mb-1.5">
                    Perfil de Picor & Ají
                  </span>
                  <AjiHeatGauge level={recipe.spiceLevel} size="md" showLabel={true} />
                </div>

                {/* Nutritional Breakdown per serving (Calories, Proteins, Carbohydrates) */}
                <div className="mb-6 p-4 bg-[#FEF9F1] rounded-2xl border border-[#EDE6DC]">
                  <div className="flex items-center justify-between mb-2.5">
                    <div className="flex items-center gap-1.5 text-xs font-bold text-[#554336] uppercase tracking-wider">
                      <Activity className="w-4 h-4 text-[#C2410C]" />
                      <span>Información Nutricional</span>
                    </div>
                    <span className="text-[11px] font-medium text-[#887364]">Por porción</span>
                  </div>

                  <div className="grid grid-cols-3 gap-2">
                    {/* Calories */}
                    <div className="bg-[#F5F0E8] p-2.5 rounded-xl text-center border border-[#EDE6DC]/60">
                      <span className="block text-[11px] font-semibold text-[#887364] uppercase tracking-wide">
                        Calorías
                      </span>
                      <span className="text-lg font-extrabold text-[#1D1C17] font-display tabular-nums">
                        {recipe.nutrition.calories}
                      </span>
                      <span className="block text-[10px] text-[#554336] font-medium">kcal</span>
                    </div>

                    {/* Proteins */}
                    <div className="bg-[#F5F0E8] p-2.5 rounded-xl text-center border border-[#EDE6DC]/60">
                      <span className="block text-[11px] font-semibold text-[#887364] uppercase tracking-wide">
                        Proteínas
                      </span>
                      <span className="text-lg font-extrabold text-[#15803D] font-display tabular-nums">
                        {recipe.nutrition.proteins}g
                      </span>
                      <span className="block text-[10px] text-[#554336] font-medium">proteína</span>
                    </div>

                    {/* Carbohydrates */}
                    <div className="bg-[#F5F0E8] p-2.5 rounded-xl text-center border border-[#EDE6DC]/60">
                      <span className="block text-[11px] font-semibold text-[#887364] uppercase tracking-wide">
                        Carbohidratos
                      </span>
                      <span className="text-lg font-extrabold text-[#8D4B00] font-display tabular-nums">
                        {recipe.nutrition.carbohydrates}g
                      </span>
                      <span className="block text-[10px] text-[#554336] font-medium">carbos</span>
                    </div>
                  </div>
                </div>
              </div>

              {/* Action buttons */}
              <div className="flex flex-col sm:flex-row gap-3 pt-4 border-t border-[#EDE6DC]">
                <button
                  type="button"
                  onClick={() => {
                    soundFx.playClick();
                    onStartCooking(recipe);
                  }}
                  className="flex-1 py-3 px-6 rounded-full bg-[#D97706] hover:bg-[#B45309] text-white font-bold text-sm shadow-md shadow-[#D97706]/30 transition-all active:scale-95 flex items-center justify-center gap-2"
                >
                  <ChefHat className="w-5 h-5" />
                  <span>Empezar a Cocinar</span>
                </button>

                <button
                  type="button"
                  onClick={() => {
                    soundFx.playClick();
                    onToggleSave(recipe.id);
                  }}
                  className="py-3 px-5 rounded-full border-[1.5px] border-[#C2410C] text-[#C2410C] hover:bg-[#C2410C]/5 font-semibold text-sm transition-colors text-center"
                >
                  {isSaved ? 'En Favoritos' : 'Guardar Receta'}
                </button>
              </div>
            </div>
          </div>
        </div>

        {/* 2-Pane Details Section (Material 3 Tablet / Desktop architecture) */}
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-start">
          {/* Left Column: Ingredients & Scaling */}
          <div className="lg:col-span-5 space-y-6">
            <div className="bg-[#F5F0E8] rounded-3xl p-6 border border-[#EDE6DC] shadow-sm">
              <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-3 mb-5 border-b border-[#EDE6DC] pb-4">
                <div>
                  <h2 className="font-display text-xl font-bold text-[#1D1C17]">Ingredientes</h2>
                  <p className="text-xs text-[#554336]">
                    {checkedCount} de {totalIngredients} listos ({completionPercent}%)
                  </p>
                </div>
                <QuantityStepper
                  servings={servings}
                  minServings={1}
                  maxServings={16}
                  onChange={(newCount) => setServings(newCount)}
                />
              </div>

              {/* Progress bar */}
              <div className="w-full bg-[#EDE6DC] rounded-full h-1.5 mb-5 overflow-hidden">
                <div
                  className="bg-[#15803D] h-full rounded-full transition-all duration-300"
                  style={{ width: `${completionPercent}%` }}
                />
              </div>

              {/* Ingredient list rows with 24px circular interactive checks */}
              <ul className="space-y-3">
                {recipe.ingredients.map((ing) => {
                  const isChecked = !!checkedIngredients[ing.id];
                  const scaledAmount =
                    typeof ing.baseAmount === 'number'
                      ? Math.round(ing.baseAmount * scaleRatio * 10) / 10
                      : ing.baseAmount;

                  return (
                    <li
                      key={ing.id}
                      onClick={() => toggleIngredientCheck(ing.id)}
                      className={`group cursor-pointer flex items-start gap-3 p-2.5 rounded-2xl transition-all ${
                        isChecked
                          ? 'bg-[#E7E2DA]/60 opacity-60'
                          : 'hover:bg-[#EDE6DC]/70'
                      }`}
                    >
                      {/* 24px Circular checkbox */}
                      <button
                        type="button"
                        className={`w-6 h-6 rounded-full shrink-0 mt-0.5 flex items-center justify-center transition-all ${
                          isChecked
                            ? 'bg-[#15803D] text-white shadow-sm'
                            : 'border-2 border-[#A8A29E] bg-transparent group-hover:border-[#15803D]'
                        }`}
                        aria-label={`Marcar ${ing.name}`}
                      >
                        {isChecked && <Check className="w-3.5 h-3.5 stroke-[3]" />}
                      </button>

                      <div className="flex-1 text-sm">
                        <div className="flex items-baseline justify-between gap-2">
                          <span
                            className={`font-semibold ${
                              isChecked
                                ? 'line-through text-[#887364]'
                                : 'text-[#1D1C17]'
                            }`}
                          >
                            {ing.name}
                          </span>
                          <span className="font-bold text-[#8D4B00] tabular-nums whitespace-nowrap text-xs">
                            {scaledAmount} {ing.unit}
                          </span>
                        </div>
                        {ing.notes && (
                          <p className="text-xs text-[#554336] mt-0.5 italic">
                            {ing.notes}
                          </p>
                        )}
                      </div>
                    </li>
                  );
                })}
              </ul>
            </div>

            {/* Beverage Pairing Card */}
            <div className="bg-[#581C87]/5 rounded-3xl p-6 border border-[#581C87]/20">
              <div className="flex items-center gap-2 mb-2">
                <Wine className="w-5 h-5 text-[#581C87]" />
                <h3 className="font-display text-base font-bold text-[#581C87]">
                  Maridaje de Autor
                </h3>
              </div>
              <p className="text-sm font-bold text-[#1D1C17] mb-1">
                {recipe.pairing.drink}
              </p>
              <p className="text-xs text-[#554336] leading-relaxed">
                {recipe.pairing.description}
              </p>
            </div>
          </div>

          {/* Right Column: Cooking Steps preview & Cultural Context */}
          <div className="lg:col-span-7 space-y-6">
            {/* Cultural History Box */}
            <div className="bg-[#F8F3EB] rounded-3xl p-6 border border-[#EDE6DC]">
              <div className="flex items-center gap-2 mb-2">
                <Sparkles className="w-4 h-4 text-[#D97706]" />
                <h3 className="font-display text-base font-bold text-[#1D1C17]">
                  Memoria e Historia Culinaria
                </h3>
              </div>
              <p className="text-sm text-[#554336] leading-relaxed">
                {recipe.culturalHistory}
              </p>
            </div>

            {/* Preparation Steps Preview */}
            <div className="bg-[#F5F0E8] rounded-3xl p-6 border border-[#EDE6DC] shadow-sm">
              <div className="flex items-center justify-between mb-6">
                <div>
                  <h2 className="font-display text-xl font-bold text-[#1D1C17]">
                    Pasos de Preparación ({recipe.steps.length})
                  </h2>
                  <p className="text-xs text-[#554336]">
                    Puedes revisar los pasos o abrir el modo cocina guiado
                  </p>
                </div>
                <button
                  type="button"
                  onClick={() => {
                    soundFx.playClick();
                    onStartCooking(recipe);
                  }}
                  className="py-2 px-4 rounded-full bg-[#D97706] hover:bg-[#B45309] text-white text-xs font-bold shadow-sm transition-all flex items-center gap-1.5"
                >
                  <ChefHat className="w-3.5 h-3.5" />
                  <span>Modo Cocina</span>
                </button>
              </div>

              <div className="space-y-4">
                {recipe.steps.map((st) => (
                  <div
                    key={st.stepNumber}
                    className="p-5 rounded-2xl bg-[#FEF9F1] border border-[#EDE6DC] hover:border-[#dbc2b0] transition-colors"
                  >
                    <div className="flex items-start justify-between gap-3 mb-2">
                      <div className="flex items-center gap-2.5">
                        <span className="w-7 h-7 rounded-full bg-[#D97706]/15 text-[#8D4B00] font-bold text-xs flex items-center justify-center font-display tabular-nums">
                          {st.stepNumber}
                        </span>
                        <h4 className="font-display text-base font-bold text-[#1D1C17]">
                          {st.title}
                        </h4>
                      </div>

                      {st.timerDurationSeconds && (
                        <button
                          type="button"
                          onClick={() => {
                            soundFx.playClick();
                            onStartTimer(
                              st.timerLabel || `Paso ${st.stepNumber}`,
                              st.timerDurationSeconds!,
                              recipe.title
                            );
                          }}
                          className="px-3 py-1 rounded-full bg-[#581C87]/10 hover:bg-[#581C87]/20 text-[#581C87] text-xs font-semibold flex items-center gap-1.5 transition-colors"
                          title="Iniciar temporizador para este paso"
                        >
                          <TimerIcon className="w-3.5 h-3.5" />
                          <span className="tabular-nums">
                            {Math.round(st.timerDurationSeconds / 60)} min
                          </span>
                        </button>
                      )}
                    </div>

                    <p className="text-sm text-[#554336] leading-relaxed pl-9 mb-3">
                      {st.instruction}
                    </p>

                    {st.chefSecret && (
                      <div className="ml-9 p-3 rounded-xl bg-[#F8F3EB] border border-[#EDE6DC] text-xs text-[#554336] flex items-start gap-2">
                        <Flame className="w-4 h-4 text-[#C2410C] shrink-0 mt-0.5" />
                        <div>
                          <strong className="text-[#C2410C] font-semibold">El Secreto del Chef: </strong>
                          {st.chefSecret}
                        </div>
                      </div>
                    )}
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};
