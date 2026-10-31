import { Recipe } from '../types';
import { COSTA_RECIPES } from './recipesCosta';
import { SIERRA_RECIPES } from './recipesSierra';
import { SELVA_RECIPES } from './recipesSelva';

export { COSTA_RECIPES, SIERRA_RECIPES, SELVA_RECIPES };

export const RECIPES_DATA: Recipe[] = [
  ...COSTA_RECIPES,
  ...SIERRA_RECIPES,
  ...SELVA_RECIPES,
];
