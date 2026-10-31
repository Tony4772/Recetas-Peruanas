export type Region = 'Costa' | 'Sierra' | 'Selva';

export type Category = 
  | 'all'
  | 'ceviches'
  | 'stews'
  | 'stir_fries'
  | 'appetizers'
  | 'soups'
  | 'desserts'
  | 'beverages';

export interface Ingredient {
  id: string;
  name: string;
  baseAmount: number; // For default 4 servings
  unit: string;
  notes?: string;
  category: 'produce' | 'seafood' | 'meat' | 'spices' | 'pantry';
}

export interface CookingStep {
  stepNumber: number;
  title: string;
  instruction: string;
  chefSecret?: string;
  timerDurationSeconds?: number;
  timerLabel?: string;
}

export interface NutritionalInfo {
  calories: number; // kcal per serving
  proteins: number; // grams per serving
  carbohydrates: number; // grams per serving
  fats?: number; // optional grams per serving
}

export interface Recipe {
  id: string;
  title: string;
  quechuaOrLocalName?: string;
  subtitle: string;
  description: string;
  region: Region;
  category: Category;
  prepTimeMinutes: number;
  cookTimeMinutes: number;
  difficulty: 'Fácil' | 'Intermedio' | 'Maestro';
  spiceLevel: 0 | 1 | 2 | 3 | 4 | 5; // Ají gauge 0-5
  defaultServings: number;
  imageUrl: string;
  tags: string[];
  nutrition: NutritionalInfo;
  pairing: {
    drink: string;
    description: string;
  };
  culturalHistory: string;
  ingredients: Ingredient[];
  steps: CookingStep[];
}

export interface ChiliInfo {
  id: string;
  name: string;
  scientificName: string;
  scoville: string;
  heatLevel: 1 | 2 | 3 | 4 | 5;
  colorHex: string;
  origin: string;
  flavorProfile: string;
  culinaryUse: string;
  substitution: string;
  imageUrl: string;
}

export interface PantryItem {
  id: string;
  name: string;
  localName: string;
  category: string;
  description: string;
  vitalFor: string[];
  tip: string;
}
