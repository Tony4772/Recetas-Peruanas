import React, { useState } from 'react';
import { Clock, ChefHat, Bookmark, Sparkles } from 'lucide-react';
import { Recipe } from '../types';
import { AjiHeatGauge } from './AjiHeatGauge';
import { soundFx } from '../utils/audio';

interface RecipeCardProps {
  recipe: Recipe;
  isSaved: boolean;
  onSelect: (recipe: Recipe) => void;
  onToggleSave: (recipeId: string) => void;
  onStartCooking: (recipe: Recipe) => void;
}

export const RecipeCard: React.FC<RecipeCardProps> = ({
  recipe,
  isSaved,
  onSelect,
  onToggleSave,
  onStartCooking,
}) => {
  const [imageError, setImageError] = useState(false);

  return (
    <article
      onClick={() => onSelect(recipe)}
      className="group cursor-pointer rounded-2xl bg-[#F5F0E8] hover:bg-[#EDE6DC] transition-all duration-300 overflow-hidden flex flex-col justify-between shadow-sm hover:shadow-xl hover:-translate-y-1"
    >
      <div>
        {/* Edge-to-edge organic media header */}
        <div className="relative aspect-[16/10] overflow-hidden bg-[#E7E2DA]">
          {!imageError ? (
            <img
              src={recipe.imageUrl}
              alt={recipe.title}
              referrerPolicy="no-referrer"
              onError={() => setImageError(true)}
              className="w-full h-full object-cover transition-transform duration-700 group-hover:scale-105"
            />
          ) : (
            <div className="w-full h-full flex flex-col items-center justify-center p-6 text-center bg-gradient-to-br from-[#F2EDE5] to-[#E7E2DA]">
              <Sparkles className="w-8 h-8 text-[#8D4B00] mb-2" />
              <p className="font-display font-semibold text-[#1D1C17]">{recipe.title}</p>
            </div>
          )}

          {/* Region heritage tag & save action */}
          <div className="absolute top-3 left-3 right-3 flex items-center justify-between">
            <span className="px-3 py-1 rounded-full text-xs font-semibold bg-[#FEF9F1]/90 backdrop-blur-md text-[#581C87] shadow-sm">
              {recipe.region}
            </span>

            <button
              type="button"
              onClick={(e) => {
                e.stopPropagation();
                soundFx.playClick();
                onToggleSave(recipe.id);
              }}
              className={`w-9 h-9 rounded-full flex items-center justify-center backdrop-blur-md shadow-md transition-transform active:scale-90 ${
                isSaved
                  ? 'bg-[#C2410C] text-white'
                  : 'bg-[#FEF9F1]/90 text-[#554336] hover:text-[#1D1C17]'
              }`}
              aria-label={isSaved ? 'Remover de favoritos' : 'Guardar en favoritos'}
            >
              <Bookmark className={`w-4 h-4 ${isSaved ? 'fill-current' : ''}`} />
            </button>
          </div>

          {/* Floating metadata bar on image bottom */}
          <div className="absolute bottom-2 left-2 right-2 flex items-center justify-between text-xs font-semibold text-[#FEF9F1] bg-[#1D1C17]/75 backdrop-blur-md rounded-full px-3 py-1.5">
            <div className="flex items-center gap-1.5">
              <Clock className="w-3.5 h-3.5 text-[#F59E0B]" />
              <span className="tabular-nums">{recipe.prepTimeMinutes + recipe.cookTimeMinutes} min</span>
            </div>
            <span aria-hidden="true">·</span>
            <span className="capitalize">{recipe.difficulty}</span>
            <span aria-hidden="true">·</span>
            <span>{recipe.defaultServings} porciones</span>
          </div>
        </div>

        {/* Content body */}
        <div className="p-5">
          <div className="flex items-start justify-between gap-2 mb-1">
            <h3 className="font-display text-lg font-bold text-[#1D1C17] group-hover:text-[#8D4B00] transition-colors leading-snug">
              {recipe.title}
            </h3>
          </div>

          {recipe.quechuaOrLocalName && (
            <p className="text-xs font-semibold text-[#8D4B00] uppercase tracking-wider mb-2">
              {recipe.quechuaOrLocalName}
            </p>
          )}

          <p className="text-xs text-[#554336] line-clamp-2 leading-relaxed mb-4">
            {recipe.description}
          </p>

          {/* Ají Heat Gauge */}
          <div className="pt-2 border-t border-[#EDE6DC] flex items-center justify-between">
            <AjiHeatGauge level={recipe.spiceLevel} size="sm" showLabel={true} />
          </div>
        </div>
      </div>

      {/* Card actions */}
      <div className="px-5 pb-5 pt-0 flex items-center gap-2">
        <button
          type="button"
          onClick={(e) => {
            e.stopPropagation();
            soundFx.playClick();
            onSelect(recipe);
          }}
          className="flex-1 py-2 px-3 text-xs font-semibold text-[#8D4B00] hover:text-[#5d1900] bg-[#EDE6DC] hover:bg-[#E7E0D2] rounded-full transition-colors text-center"
        >
          Ver Receta
        </button>

        <button
          type="button"
          onClick={(e) => {
            e.stopPropagation();
            soundFx.playClick();
            onStartCooking(recipe);
          }}
          className="py-2 px-3.5 text-xs font-semibold text-white bg-[#D97706] hover:bg-[#B45309] rounded-full shadow-sm hover:shadow-md transition-all active:scale-95 flex items-center gap-1.5"
        >
          <ChefHat className="w-3.5 h-3.5" />
          <span>Cocinar</span>
        </button>
      </div>
    </article>
  );
};
