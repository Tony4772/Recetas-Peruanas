import React from 'react';
import { Minus, Plus, Users } from 'lucide-react';
import { soundFx } from '../utils/audio';

interface QuantityStepperProps {
  servings: number;
  minServings?: number;
  maxServings?: number;
  step?: number;
  onChange: (newServings: number) => void;
}

export const QuantityStepper: React.FC<QuantityStepperProps> = ({
  servings,
  minServings = 1,
  maxServings = 16,
  step = 1,
  onChange,
}) => {
  const handleDecrement = () => {
    if (servings > minServings) {
      soundFx.playClick();
      onChange(Math.max(minServings, servings - step));
    }
  };

  const handleIncrement = () => {
    if (servings < maxServings) {
      soundFx.playClick();
      onChange(Math.min(maxServings, servings + step));
    }
  };

  return (
    <div className="inline-flex items-center gap-3">
      <div className="flex items-center bg-[#EDE6DC] rounded-full p-1 shadow-inner">
        <button
          type="button"
          onClick={handleDecrement}
          disabled={servings <= minServings}
          className="w-9 h-9 flex items-center justify-center rounded-full text-[#1D1C17] hover:bg-[#E7E0D2] active:scale-95 disabled:opacity-30 disabled:pointer-events-none transition-all focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#8D4B00]"
          aria-label="Disminuir porciones"
        >
          <Minus className="w-4 h-4 stroke-[2.5]" />
        </button>

        <div className="px-3 min-w-[72px] text-center">
          <span className="text-base font-bold text-[#1D1C17] tabular-nums">
            {servings}
          </span>
          <span className="text-xs text-[#554336] ml-1 font-medium">
            {servings === 1 ? 'porción' : 'porciones'}
          </span>
        </div>

        <button
          type="button"
          onClick={handleIncrement}
          disabled={servings >= maxServings}
          className="w-9 h-9 flex items-center justify-center rounded-full text-[#1D1C17] hover:bg-[#E7E0D2] active:scale-95 disabled:opacity-30 disabled:pointer-events-none transition-all focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#8D4B00]"
          aria-label="Aumentar porciones"
        >
          <Plus className="w-4 h-4 stroke-[2.5]" />
        </button>
      </div>

      <div className="hidden sm:flex items-center text-xs text-[#554336] gap-1">
        <Users className="w-3.5 h-3.5 text-[#8D4B00]" />
        <span>Cantidades escaladas automáticamente</span>
      </div>
    </div>
  );
};
