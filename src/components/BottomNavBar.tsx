import React from 'react';
import { Utensils, Sparkles, Bookmark, Code2 } from 'lucide-react';
import { soundFx } from '../utils/audio';

interface BottomNavBarProps {
  currentTab: 'feed' | 'market' | 'saved' | 'kotlin';
  onSelectTab: (tab: 'feed' | 'market' | 'saved' | 'kotlin') => void;
  savedCount: number;
}

export const BottomNavBar: React.FC<BottomNavBarProps> = ({
  currentTab,
  onSelectTab,
  savedCount,
}) => {
  return (
    <nav className="md:hidden fixed bottom-0 left-0 right-0 z-40 bg-[#FEF9F1]/95 backdrop-blur-md border-t border-[#EDE6DC] pb-safe shadow-lg">
      <div className="grid grid-cols-4 items-center h-16 max-w-md mx-auto">
        <button
          type="button"
          onClick={() => {
            soundFx.playClick();
            onSelectTab('feed');
          }}
          className="flex flex-col items-center justify-center min-h-[44px] transition-colors"
        >
          <div
            className={`w-8 h-7 rounded-full flex items-center justify-center transition-all ${
              currentTab === 'feed'
                ? 'bg-[#D97706]/15 text-[#8D4B00]'
                : 'text-[#887364]'
            }`}
          >
            <Utensils className="w-4 h-4" />
          </div>
          <span
            className={`text-[9px] tracking-tight mt-0.5 ${
              currentTab === 'feed' ? 'font-bold text-[#8D4B00]' : 'font-medium text-[#887364]'
            }`}
          >
            Recetas
          </span>
        </button>

        <button
          type="button"
          onClick={() => {
            soundFx.playClick();
            onSelectTab('market');
          }}
          className="flex flex-col items-center justify-center min-h-[44px] transition-colors"
        >
          <div
            className={`w-8 h-7 rounded-full flex items-center justify-center transition-all ${
              currentTab === 'market'
                ? 'bg-[#D97706]/15 text-[#8D4B00]'
                : 'text-[#887364]'
            }`}
          >
            <Sparkles className="w-4 h-4" />
          </div>
          <span
            className={`text-[9px] tracking-tight mt-0.5 ${
              currentTab === 'market' ? 'font-bold text-[#8D4B00]' : 'font-medium text-[#887364]'
            }`}
          >
            Mercado
          </span>
        </button>

        <button
          type="button"
          onClick={() => {
            soundFx.playClick();
            onSelectTab('saved');
          }}
          className="flex flex-col items-center justify-center min-h-[44px] transition-colors relative"
        >
          <div
            className={`w-8 h-7 rounded-full flex items-center justify-center transition-all relative ${
              currentTab === 'saved'
                ? 'bg-[#D97706]/15 text-[#8D4B00]'
                : 'text-[#887364]'
            }`}
          >
            <Bookmark className="w-4 h-4" />
            {savedCount > 0 && (
              <span className="absolute -top-1 -right-1 w-3.5 h-3.5 rounded-full bg-[#AC3400] text-white text-[8px] font-bold flex items-center justify-center tabular-nums">
                {savedCount}
              </span>
            )}
          </div>
          <span
            className={`text-[9px] tracking-tight mt-0.5 ${
              currentTab === 'saved' ? 'font-bold text-[#8D4B00]' : 'font-medium text-[#887364]'
            }`}
          >
            Guardados
          </span>
        </button>

        <button
          type="button"
          onClick={() => {
            soundFx.playClick();
            onSelectTab('kotlin');
          }}
          className="flex flex-col items-center justify-center min-h-[44px] transition-colors"
        >
          <div
            className={`w-8 h-7 rounded-full flex items-center justify-center transition-all ${
              currentTab === 'kotlin'
                ? 'bg-[#15803D]/15 text-[#15803D]'
                : 'text-[#887364]'
            }`}
          >
            <Code2 className="w-4 h-4" />
          </div>
          <span
            className={`text-[9px] tracking-tight mt-0.5 ${
              currentTab === 'kotlin' ? 'font-bold text-[#15803D]' : 'font-medium text-[#887364]'
            }`}
          >
            Kotlin
          </span>
        </button>
      </div>
    </nav>
  );
};
