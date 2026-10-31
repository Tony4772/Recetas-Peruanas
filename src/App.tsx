import React, { useState, useEffect, useMemo } from 'react';
import {
  Search,
  SlidersHorizontal,
  Flame,
  ChefHat,
  Sparkles,
  MapPin,
  Clock,
  Compass,
  UtensilsCrossed,
  Filter
} from 'lucide-react';
import { RECIPES_DATA } from './data/recipes';
import { Recipe, Category, Region } from './types';
import { TopBar } from './components/TopBar';
import { BottomNavBar } from './components/BottomNavBar';
import { RecipeCard } from './components/RecipeCard';
import { RecipeDetail } from './components/RecipeDetail';
import { CookingMode } from './components/CookingMode';
import { MercadoGuide } from './components/MercadoGuide';
import { SavedRecipes } from './components/SavedRecipes';
import { KitchenTimerPill, ActiveTimer } from './components/KitchenTimerPill';
import { AjiHeatGauge } from './components/AjiHeatGauge';
import { KotlinCodeViewer } from './components/KotlinCodeViewer';
import { OfflineIndicator } from './components/OfflineIndicator';
import { useOnlineStatus } from './hooks/useOnlineStatus';
import { soundFx } from './utils/audio';

const CATEGORIES: { id: Category; label: string }[] = [
  { id: 'all', label: 'Todas las Recetas' },
  { id: 'ceviches', label: 'Ceviches & Tiraditos' },
  { id: 'stir_fries', label: 'Salteados al Wok' },
  { id: 'stews', label: 'Guisos & Secos' },
  { id: 'appetizers', label: 'Entradas & Causas' },
  { id: 'soups', label: 'Chupes & Sopas' },
  { id: 'desserts', label: 'Postres Tradicionales' },
  { id: 'beverages', label: 'Bebidas & Cócteles' },
];

const REGIONS: (Region | 'Todas')[] = ['Todas', 'Costa', 'Sierra', 'Selva'];

export default function App() {
  const { isOnline, showReconnectedBanner, dismissReconnected } = useOnlineStatus();
  const [currentTab, setCurrentTab] = useState<'feed' | 'market' | 'saved' | 'kotlin'>('feed');
  const [selectedRecipe, setSelectedRecipe] = useState<Recipe | null>(null);
  const [isCookingModeActive, setIsCookingModeActive] = useState<boolean>(false);
  const [cookingRecipe, setCookingRecipe] = useState<Recipe | null>(null);
  const [isDeviceFrame, setIsDeviceFrame] = useState<boolean>(false);

  // Search and Filter States
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedCategory, setSelectedCategory] = useState<Category>('all');
  const [selectedRegion, setSelectedRegion] = useState<Region | 'Todas'>('Todas');
  const [maxSpiceLevel, setMaxSpiceLevel] = useState<number>(5);

  // Saved recipes persistence
  const [savedRecipeIds, setSavedRecipeIds] = useState<string[]>(() => {
    try {
      const stored = localStorage.getItem('andean_saved_recipes');
      return stored ? JSON.parse(stored) : ['ceviche-clasico-corvina', 'lomo-saltado-criollo'];
    } catch {
      return ['ceviche-clasico-corvina', 'lomo-saltado-criollo'];
    }
  });

  // Global Active Timer for KitchenTimerPill
  const [activeTimer, setActiveTimer] = useState<ActiveTimer | null>(null);

  const toggleSaveRecipe = (recipeId: string) => {
    setSavedRecipeIds((prev) => {
      const isAlready = prev.includes(recipeId);
      const updated = isAlready ? prev.filter((id) => id !== recipeId) : [...prev, recipeId];
      try {
        localStorage.setItem('andean_saved_recipes', JSON.stringify(updated));
      } catch {
        // ignore
      }
      return updated;
    });
  };

  const handleStartTimer = (label: string, seconds: number, recipeTitle?: string) => {
    setActiveTimer({
      id: String(Date.now()),
      label,
      recipeTitle,
      totalSeconds: seconds,
      remainingSeconds: seconds,
      isRunning: true,
    });
  };

  const handleStartCooking = (recipe: Recipe) => {
    setCookingRecipe(recipe);
    setIsCookingModeActive(true);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleSelectRecipeDetail = (recipe: Recipe) => {
    setSelectedRecipe(recipe);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  // Filtered recipes
  const filteredRecipes = useMemo(() => {
    return RECIPES_DATA.filter((recipe) => {
      const matchesSearch =
        searchQuery.trim() === '' ||
        recipe.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
        recipe.subtitle.toLowerCase().includes(searchQuery.toLowerCase()) ||
        recipe.ingredients.some((ing) => ing.name.toLowerCase().includes(searchQuery.toLowerCase())) ||
        (recipe.quechuaOrLocalName && recipe.quechuaOrLocalName.toLowerCase().includes(searchQuery.toLowerCase()));

      const matchesCategory = selectedCategory === 'all' || recipe.category === selectedCategory;
      const matchesRegion = selectedRegion === 'Todas' || recipe.region === selectedRegion;
      const matchesSpice = recipe.spiceLevel <= maxSpiceLevel;

      return matchesSearch && matchesCategory && matchesRegion && matchesSpice;
    });
  }, [searchQuery, selectedCategory, selectedRegion, maxSpiceLevel]);

  const savedRecipesList = useMemo(() => {
    return RECIPES_DATA.filter((r) => savedRecipeIds.includes(r.id));
  }, [savedRecipeIds]);

  // Spotlight Hero Recipe
  const heroRecipe = RECIPES_DATA[0];

  // If active in full-screen cooking mode:
  if (isCookingModeActive && cookingRecipe) {
    return (
      <CookingMode
        recipe={cookingRecipe}
        onExit={() => setIsCookingModeActive(false)}
        onStartTimer={handleStartTimer}
      />
    );
  }

  // If viewing a single recipe detail:
  if (selectedRecipe) {
    return (
      <>
        <OfflineIndicator
          isOnline={isOnline}
          showReconnectedBanner={showReconnectedBanner}
          onDismissReconnected={dismissReconnected}
          recipesCount={RECIPES_DATA.length}
        />
        <RecipeDetail
          recipe={selectedRecipe}
          isSaved={savedRecipeIds.includes(selectedRecipe.id)}
          onBack={() => setSelectedRecipe(null)}
          onToggleSave={toggleSaveRecipe}
          onStartCooking={handleStartCooking}
          onStartTimer={handleStartTimer}
        />
        <KitchenTimerPill timer={activeTimer} onUpdateTimer={setActiveTimer} />
      </>
    );
  }

  return (
    <div className="min-h-screen bg-[#FEF9F1] text-[#1D1C17] flex flex-col justify-between selection:bg-[#D97706]/20">
      <div>
        {/* Offline indicator banner */}
        <OfflineIndicator
          isOnline={isOnline}
          showReconnectedBanner={showReconnectedBanner}
          onDismissReconnected={dismissReconnected}
          recipesCount={RECIPES_DATA.length}
        />

        {/* Top Navigation Bar adhering to the Top Bar Contract */}
        <TopBar
          currentTab={currentTab}
          onSelectTab={(tab) => {
            setCurrentTab(tab);
            window.scrollTo({ top: 0, behavior: 'smooth' });
          }}
          savedCount={savedRecipeIds.length}
          onQuickCook={() => handleStartCooking(heroRecipe)}
          isDeviceFrame={isDeviceFrame}
          onToggleDeviceFrame={() => setIsDeviceFrame(!isDeviceFrame)}
          isOnline={isOnline}
        />

        {/* Main Content Area */}
        <div className={`max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6 pb-28 ${
          isDeviceFrame && currentTab !== 'kotlin' ? 'flex justify-center' : ''
        }`}>
          {currentTab === 'kotlin' ? (
            <KotlinCodeViewer />
          ) : currentTab === 'market' ? (
            <div className={isDeviceFrame ? 'w-full max-w-sm rounded-[40px] border-[10px] border-[#292524] shadow-2xl p-4 bg-[#FEF9F1] overflow-hidden' : ''}>
              <MercadoGuide />
            </div>
          ) : currentTab === 'saved' ? (
            <div className={isDeviceFrame ? 'w-full max-w-sm rounded-[40px] border-[10px] border-[#292524] shadow-2xl p-4 bg-[#FEF9F1] overflow-hidden' : ''}>
              <SavedRecipes
                savedRecipes={savedRecipesList}
                onSelectRecipe={handleSelectRecipeDetail}
                onToggleSave={toggleSaveRecipe}
                onStartCooking={handleStartCooking}
                onExploreFeed={() => setCurrentTab('feed')}
              />
            </div>
          ) : (
            /* Discover & Recipe Feed Screen */
            <div className={`space-y-8 ${isDeviceFrame ? 'w-full max-w-sm rounded-[40px] border-[10px] border-[#292524] shadow-2xl p-4 bg-[#FEF9F1] overflow-hidden' : ''}`}>
              {/* Hero Banner Showcase (Material 3 Extra Large rounded container) */}
              <div className="relative rounded-3xl bg-[#F5F0E8] border border-[#EDE6DC] overflow-hidden shadow-sm">
                <div className="grid grid-cols-1 lg:grid-cols-12 items-stretch">
                  <div className="lg:col-span-7 p-6 sm:p-10 flex flex-col justify-between">
                    <div>
                      <div className="flex items-center gap-2 mb-2">
                        <span className="px-3 py-1 rounded-full text-[11px] font-bold uppercase tracking-wider bg-[#581C87] text-white">
                          Plato Bandera del Perú
                        </span>
                        <span className="text-xs text-[#887364] font-medium">
                          {heroRecipe.region}
                        </span>
                      </div>

                      <h1 className="font-display text-2xl sm:text-4xl font-extrabold text-[#1D1C17] leading-tight mb-2">
                        {heroRecipe.title}
                      </h1>
                      <p className="text-xs sm:text-sm text-[#554336] leading-relaxed mb-6 max-w-xl">
                        {heroRecipe.subtitle}. Pescado fresco emulsionado con limón piurano en menos de 3 minutos para obtener la más pura leche de tigre.
                      </p>

                      <div className="flex flex-wrap items-center gap-4 text-xs font-semibold text-[#554336] mb-6">
                        <div className="flex items-center gap-1.5 bg-[#EDE6DC] px-3 py-1.5 rounded-full">
                          <Clock className="w-3.5 h-3.5 text-[#8D4B00]" />
                          <span>{heroRecipe.prepTimeMinutes + heroRecipe.cookTimeMinutes} min totales</span>
                        </div>
                        <div className="bg-[#EDE6DC] px-3 py-1.5 rounded-full">
                          <span>{heroRecipe.difficulty}</span>
                        </div>
                        <div className="bg-[#EDE6DC] px-3 py-1.5 rounded-full">
                          <AjiHeatGauge level={heroRecipe.spiceLevel} size="sm" showLabel={true} />
                        </div>
                      </div>
                    </div>

                    <div className="flex flex-wrap items-center gap-3 pt-4 border-t border-[#EDE6DC]">
                      <button
                        type="button"
                        onClick={() => {
                          soundFx.playClick();
                          handleStartCooking(heroRecipe);
                        }}
                        className="py-3 px-6 rounded-full bg-[#D97706] hover:bg-[#B45309] text-white font-bold text-xs sm:text-sm shadow-md shadow-[#D97706]/30 flex items-center gap-2 active:scale-95 transition-all"
                      >
                        <ChefHat className="w-4 h-4" />
                        <span>Cocinar Plato Bandera</span>
                      </button>

                      <button
                        type="button"
                        onClick={() => {
                          soundFx.playClick();
                          handleSelectRecipeDetail(heroRecipe);
                        }}
                        className="py-3 px-5 rounded-full border-[1.5px] border-[#C2410C] text-[#C2410C] hover:bg-[#C2410C]/5 font-semibold text-xs sm:text-sm transition-colors"
                      >
                        Ver Ingredientes y Pasos
                      </button>
                    </div>
                  </div>

                  <div className="lg:col-span-5 relative aspect-[16/10] lg:aspect-auto min-h-[260px] overflow-hidden bg-[#E7E2DA]">
                    <img
                      src={heroRecipe.imageUrl}
                      alt={heroRecipe.title}
                      referrerPolicy="no-referrer"
                      className="w-full h-full object-cover"
                    />
                  </div>
                </div>
              </div>

              {/* Search & Filter Toolbar */}
              <div className="bg-[#F5F0E8] rounded-3xl p-5 border border-[#EDE6DC] shadow-sm space-y-4">
                <div className="flex flex-col md:flex-row items-stretch md:items-center justify-between gap-4">
                  {/* Search Bar (Fully rounded pill shape 9999px) */}
                  <div className="relative flex-1">
                    <Search className="w-4 h-4 text-[#887364] absolute left-4 top-1/2 -translate-y-1/2" />
                    <input
                      type="text"
                      value={searchQuery}
                      onChange={(e) => setSearchQuery(e.target.value)}
                      placeholder="Buscar por plato, ingrediente o ají (ej: corvina, lomo, huacatay)..."
                      className="w-full pl-11 pr-4 py-3 rounded-full bg-[#FEF9F1] border border-[#EDE6DC] text-sm text-[#1D1C17] placeholder-[#887364] focus:outline-none focus:ring-2 focus:ring-[#8D4B00] transition-shadow shadow-inner"
                    />
                  </div>

                  {/* Region Selectors (Single line pills) */}
                  <div className="flex items-center gap-1.5 overflow-x-auto pb-1 md:pb-0 scrollbar-none">
                    <span className="text-xs font-bold text-[#887364] uppercase mr-1 hidden sm:inline">
                      Región:
                    </span>
                    {REGIONS.map((reg) => (
                      <button
                        key={reg}
                        type="button"
                        onClick={() => {
                          soundFx.playClick();
                          setSelectedRegion(reg);
                        }}
                        className={`py-1.5 px-3 rounded-full text-xs font-semibold whitespace-nowrap transition-colors ${
                          selectedRegion === reg
                            ? 'bg-[#8D4B00] text-white shadow-sm'
                            : 'bg-[#EDE6DC] text-[#554336] hover:bg-[#E7E0D2]'
                        }`}
                      >
                        {reg}
                      </button>
                    ))}
                  </div>
                </div>

                {/* Category Filter Chips (Pill shaped, height 36px, Surface Container High) */}
                <div className="flex items-center gap-2 overflow-x-auto pb-2 scrollbar-none">
                  {CATEGORIES.map((cat) => {
                    const isSelected = selectedCategory === cat.id;
                    return (
                      <button
                        key={cat.id}
                        type="button"
                        onClick={() => {
                          soundFx.playClick();
                          setSelectedCategory(cat.id);
                        }}
                        className={`h-9 px-4 rounded-full text-xs font-bold whitespace-nowrap flex items-center gap-1.5 transition-all active:scale-95 ${
                          isSelected
                            ? 'bg-[#C2410C] text-white shadow-sm'
                            : 'bg-[#EDE6DC] text-[#292524] hover:bg-[#E7E0D2]'
                        }`}
                      >
                        {isSelected && <span className="w-1.5 h-1.5 rounded-full bg-white" />}
                        <span>{cat.label}</span>
                      </button>
                    );
                  })}
                </div>
              </div>

              {/* Recipe Cards Feed Header */}
              <div className="flex items-center justify-between">
                <div>
                  <h2 className="font-display text-xl sm:text-2xl font-bold text-[#1D1C17]">
                    Platos & Recetas Andinas
                  </h2>
                  <p className="text-xs text-[#554336] mt-0.5">
                    {filteredRecipes.length}{' '}
                    {filteredRecipes.length === 1 ? 'receta disponible' : 'recetas disponibles'} para cocinar hoy
                  </p>
                </div>

                {/* Reset filters if any applied */}
                {(searchQuery || selectedCategory !== 'all' || selectedRegion !== 'Todas') && (
                  <button
                    type="button"
                    onClick={() => {
                      soundFx.playClick();
                      setSearchQuery('');
                      setSelectedCategory('all');
                      setSelectedRegion('Todas');
                    }}
                    className="text-xs font-bold text-[#C2410C] hover:underline"
                  >
                    Restablecer filtros
                  </button>
                )}
              </div>

              {/* Recipe Cards Grid */}
              {filteredRecipes.length === 0 ? (
                <div className="text-center py-16 px-4 bg-[#F5F0E8] rounded-3xl border border-[#EDE6DC] max-w-md mx-auto space-y-3">
                  <UtensilsCrossed className="w-10 h-10 text-[#887364] mx-auto" />
                  <h3 className="font-display text-lg font-bold text-[#1D1C17]">
                    No se encontraron recetas
                  </h3>
                  <p className="text-xs text-[#554336]">
                    Prueba modificando tus términos de búsqueda o seleccionando otra región andina.
                  </p>
                  <button
                    type="button"
                    onClick={() => {
                      setSearchQuery('');
                      setSelectedCategory('all');
                      setSelectedRegion('Todas');
                    }}
                    className="py-2 px-5 bg-[#8D4B00] text-white rounded-full text-xs font-bold"
                  >
                    Ver todas las recetas
                  </button>
                </div>
              ) : (
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 sm:gap-8">
                  {filteredRecipes.map((recipe) => (
                    <RecipeCard
                      key={recipe.id}
                      recipe={recipe}
                      isSaved={savedRecipeIds.includes(recipe.id)}
                      onSelect={handleSelectRecipeDetail}
                      onToggleSave={toggleSaveRecipe}
                      onStartCooking={handleStartCooking}
                    />
                  ))}
                </div>
              )}
            </div>
          )}
        </div>
      </div>

      {/* Persistent Floating Kitchen Timer Pill */}
      <KitchenTimerPill timer={activeTimer} onUpdateTimer={setActiveTimer} />

      {/* Mobile Touch-First Bottom Navigation Bar */}
      <BottomNavBar
        currentTab={currentTab}
        onSelectTab={(tab) => {
          setCurrentTab(tab);
          window.scrollTo({ top: 0, behavior: 'smooth' });
        }}
        savedCount={savedRecipeIds.length}
      />
    </div>
  );
}
