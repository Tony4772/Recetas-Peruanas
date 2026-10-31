import React from 'react';

interface AjiHeatGaugeProps {
  level: number; // 0 to 5
  showLabel?: boolean;
  size?: 'sm' | 'md' | 'lg';
}

const HEAT_DESCRIPTIONS: Record<number, string> = {
  0: 'Sin picor · Aromático',
  1: 'Picor suave · Fresco',
  2: 'Picor medio · Sabor criollo',
  3: 'Picor moderado · Vibrante',
  4: 'Picor intenso · Fuego andino',
  5: 'Picor volcánico · Rocoto puro',
};

export const AjiHeatGauge: React.FC<AjiHeatGaugeProps> = ({
  level,
  showLabel = true,
  size = 'md',
}) => {
  const iconSize = size === 'sm' ? 'w-3.5 h-3.5' : size === 'lg' ? 'w-5 h-5' : 'w-4 h-4';
  const textSize = size === 'sm' ? 'text-xs' : 'text-sm';

  return (
    <div className="inline-flex items-center gap-1.5" title={`Nivel de Ají: ${level}/5`}>
      <div className="flex items-center gap-0.5">
        {[1, 2, 3, 4, 5].map((idx) => {
          const isFilled = idx <= level;
          return (
            <svg
              key={idx}
              className={`${iconSize} transition-colors duration-200 ${
                isFilled ? 'text-[#C2410C]' : 'text-[#dbc2b0]/50'
              }`}
              viewBox="0 0 24 24"
              fill={isFilled ? 'currentColor' : 'none'}
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
              aria-hidden="true"
            >
              {/* Stylized Andean Ají pepper silhouette with curved stem */}
              <path d="M14.5 4c-.5-1-1.5-2-3-2-1 0-1.5.5-2 1.5-1 2 .5 3.5 1 4.5" />
              <path d="M10.5 8c2.5 0 6 2 6 6.5 0 5-4.5 7.5-6.5 7.5S4 18.5 4 14c0-4 3.5-6 6.5-6z" />
              <path d="M12 9.5c1.5 1 2.5 2.5 2.5 4.5" strokeWidth="1.5" />
            </svg>
          );
        })}
      </div>
      {showLabel && (
        <span className={`${textSize} font-medium text-[#554336] tabular-nums`}>
          {level > 0 ? `${level}/5 · ${HEAT_DESCRIPTIONS[level] || ''}` : '0/5 · Suave'}
        </span>
      )}
    </div>
  );
};
