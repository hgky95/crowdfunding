interface CampaignCardProps {
  title: string;
  description: string;
  imageUrl: string;
  raised: string;
  daysLeft: number;
  creator: string;
}

export const CampaignCard = ({
  title,
  description,
  imageUrl,
  raised,
  daysLeft,
  creator,
}: CampaignCardProps) => {
  return (
    <div className="rounded-lg border border-black/[.08] dark:border-white/[.145] overflow-hidden hover:shadow-lg transition-shadow">
      <img src={imageUrl} alt={title} className="w-full h-48 object-cover" />
      <div className="p-4">
        <h3 className="text-lg font-semibold mb-2">{title}</h3>
        <p className="text-sm text-gray-600 dark:text-gray-300 mb-4">
          {description}
        </p>
        <div className="flex justify-between text-sm">
          <span>Raised: {raised}</span>
          <span>{daysLeft} days left</span>
        </div>
        <div className="mt-2 text-sm text-gray-500">
          by {creator.slice(0, 6)}...{creator.slice(-4)}
        </div>
      </div>
    </div>
  );
};
