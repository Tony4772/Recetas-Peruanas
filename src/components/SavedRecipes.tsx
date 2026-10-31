import React, { useState, useEffect } from 'react';
import { Bookmark, ChefHat, Trash2, Edit3, Check, Clock } from 'lucide-react';
import { Recipe } from '../types';
import { RecipeCard } from './RecipeCard';
import { soundFx } from '../utils/audio';

interface SavedRecipesProps {
  savedRecipes: Recipe[];
  onSelectRecipe: (recipe: Recipe) => void;
  onToggleSave: (id: string) => void;
  onStartCooking: (recipe: Recipe) => void;
  onExploreFeed: () => void;
}

export const SavedRecipes: React.FC<SavedRecipesProps> = ({
  savedRecipes,
  onSelectRecipe,
  onToggleSave,
  onStartCooking,
  onExploreFeed,
}) => {
  const [notes, setNotes] = useState<Record<string, string>>(() => {
    try {
      const stored = localStorage.getItem('andean_culinary_notes');
      return stored ? JSON.parse(stored) : {};
    } catch {
      return {};
    }
  });

  const [editingRecipeId, setEditingRecipeId] = useState<string | null>(null);
  const [currentNoteDraft, setCurrentNoteDraft] = useState('');

  const handleStartEdit = (id: string, existingNote: string) => {
    soundFx.playClick();
    setEditingRecipeId(id);
    setCurrentNoteDraft(existingNote || '');
  };

  const handleSaveNote = (id: string) => {
    soundFx.playClick();
    const updated = { ...notes, [id]: currentNoteDraft.trim() };
    setNotes(updated);
    try {
      localStorage.setItem('andean_culinary_notes', JSON.stringify(updated));
    } catch {
      // ignore
    }
    setEditingRecipeId(null);
  };

  return (
    <div className="space-y-8">
      {/* Editorial Header */}
      <div className="bg-[#F5F0E8] rounded-3xl p-6 sm:p-10 border border-[#EDE6DC]">
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
          <div>
            <span className="text-xs font-bold uppercase tracking-widest text-[#8D4B00] block mb-1">
              Cuaderno Personal de Cocina
            </span>
            <h1 className="font-display text-2xl sm:text-4xl font-extrabold text-[#1D1C17]">
              Mis Recetas & Notas de Cata ({savedRecipes.length})
            </h1>
            <p className="text-xs sm:text-sm text-[#554336] mt-1">
              Tus platos favoritos guardados para cocinar en casa, con anotaciones personalizadas sobre sazón y tiempos.
            </p>
            <div className="flex items-center gap-2 mt-3">
              <span className="inline-flex items-center gap-1.5 px-2.5 py-0.5 rounded-full text-[11px] font-bold bg-[#15803D]/10 text-[#15803D] border border-[#15803D]/25">
                <Check className="w-3.5 h-3.5" />
                <span>Disponibles 100% Sin Conexión</span>
              </span>
            </div>
          </div>

          {savedRecipes.length > 0 && (
            <button
              type="button"
              onClick={() => {
                soundFx.playClick();
                onStartCooking(savedRecipes[0]);
              }}
              className="py-3 px-6 rounded-full bg-[#D97706] hover:bg-[#B45309] text-white font-bold text-xs sm:text-sm shadow-md flex items-center gap-2 whitespace-nowrap self-start sm:self-auto"
            >
              <ChefHat className="w-4 h-4" />
              <span>Cocinar Primer Favorito</span>
            </button>
          )}
        </div>
      </div>

      {savedRecipes.length === 0 ? (
        <div className="text-center py-16 px-4 bg-[#F8F3EB] rounded-3xl border border-[#EDE6DC] max-w-md mx-auto space-y-4">
          <div className="w-16 h-16 rounded-full bg-[#EDE6DC] text-[#887364] flex items-center justify-center mx-auto">
            <Bookmark className="w-8 h-8" />
          </div>
          <div>
            <h3 className="font-display text-lg font-bold text-[#1D1C17]">
              Aún no tienes recetas guardadas
            </h3>
            <p className="text-xs text-[#554336] mt-1 leading-relaxed">
              Explora nuestro recetario tradicional y presiona el ícono de marcador para guardar tus platos preferidos aquí.
            </p>
          </div>
          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onExploreFeed();
            }}
            className="py-2.5 px-6 rounded-full bg-[#8D4B00] text-white text-xs font-bold shadow-md hover:bg-[#6e3900] transition-all"
          >
            Explorar Recetas Tradicionales
          </button>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {savedRecipes.map((recipe) => {
            const recipeNote = notes[recipe.id] || '';
            const isEditing = editingRecipeId === recipe.id;

            return (
              <div
                key={recipe.id}
                className="bg-[#F5F0E8] rounded-3xl overflow-hidden border border-[#EDE6DC] shadow-sm flex flex-col justify-between"
              >
                <div>
                  <div className="relative aspect-[16/10] overflow-hidden bg-[#E7E2DA]">
                    <img
                      src={recipe.imageUrl}
                      alt={recipe.title}
                      referrerPolicy="no-referrer"
                      className="w-full h-full object-cover"
                    />
                    <div className="absolute top-3 right-3">
                      <button
                        type="button"
                        onClick={() => onToggleSave(recipe.id)}
                        className="w-9 h-9 rounded-full bg-[#AC3400] text-white flex items-center justify-center shadow-md hover:bg-[#832600] transition-colors"
                        title="Remover de favoritos"
                      >
                        <Trash2 className="w-4 h-4" />
                      </button>
                    </div>
                    <div className="absolute bottom-2 left-2 bg-[#1D1C17]/80 backdrop-blur-md rounded-full px-3 py-1 text-[11px] font-semibold text-white">
                      {recipe.prepTimeMinutes + recipe.cookTimeMinutes} min · {recipe.difficulty}
                    </div>
                  </div>

                  <div className="p-5">
                    <span className="text-[11px] font-bold text-[#581C87] uppercase tracking-wider">
                      {recipe.region}
                    </span>
                    <h3 className="font-display text-lg font-bold text-[#1D1C17] mt-0.5 mb-2">
                      {recipe.title}
                    </h3>
                    <p className="text-xs text-[#554336] line-clamp-2 leading-relaxed mb-4">
                      {recipe.description}
                    </p>

                    {/* Personal Tasting Note Box */}
                    <div className="p-3 bg-[#FEF9F1] rounded-2xl border border-[#EDE6DC] text-xs">
                      <div className="flex items-center justify-between mb-1">
                        <span className="font-bold text-[#8D4B00] flex items-center gap-1">
                          <Edit3 className="w-3 h-3" />
                          <span>Nota del Cocinero:</span>
                        </span>
                        {!isEditing && (
                          <button
                            type="button"
                            onClick={() => handleStartEdit(recipe.id, recipeNote)}
                            className="text-[#887364] hover:text-[#1D1C17] text-[11px] font-semibold"
                          >
                            {recipeNote ? 'Editar' : '+ Añadir nota'}
                          </button>
                        )}
                      </div>

                      {isEditing ? (
                        <div className="space-y-2 mt-2">
                          <textarea
                            value={currentNoteDraft}
                            onChange={(e) => setCurrentNoteDraft(e.target.value)}
                            placeholder="Ej: Aumentar un toque de limón piurano; comprar corvina fresca en el puerto..."
                            rows={2}
                            className="w-full p-2 text-xs rounded-xl bg-white border border-[#EDE6DC] text-[#1D1C17] focus:outline-none focus:ring-1 focus:ring-[#8D4B00]"
                          />
                          <div className="flex justify-end gap-1.5">
                            <button
                              type="button"
                              onClick={() => setEditingRecipeId(null)}
                              className="px-2.5 py-1 text-[11px] text-[#554336]"
                            >
                              Cancelar
                            </button>
                            <button
                              type="button"
                              onClick={() => handleSaveNote(recipe.id)}
                              className="px-3 py-1 bg-[#8D4B00] text-white text-[11px] font-bold rounded-full flex items-center gap-1"
                            >
                              <Check className="w-3 h-3" /> Guardar
                            </button>
                          </div>
                        </div>
                      ) : (
                        <p className="text-[#554336] italic mt-0.5">
                          {recipeNote || 'Sin notas aún. Anota tus ajustes de picor o ingredientes.'}
                        </p>
                      )}
                    </div>
                  </div>
                </div>

                <div className="px-5 pb-5 pt-0 flex items-center gap-2">
                  <button
                    type="button"
                    onClick={() => {
                      soundFx.playClick();
                      onSelectRecipe(recipe);
                    }}
                    className="flex-1 py-2 px-3 text-xs font-semibold text-[#8D4B00] bg-[#EDE6DC] hover:bg-[#E7E0D2] rounded-full transition-colors text-center"
                  >
                    Ver Detalles
                  </button>
                  <button
                    type="button"
                    onClick={() => {
                      soundFx.playClick();
                      onStartCooking(recipe);
                    }}
                    className="py-2 px-4 text-xs font-semibold text-white bg-[#D97706] hover:bg-[#B45309] rounded-full shadow-sm flex items-center gap-1.5"
                  >
                    <ChefHat className="w-3.5 h-3.5" />
                    <span>Cocinar</span>
                  </button>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};
